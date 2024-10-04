package com.hhplus.clean;

import com.hhplus.clean.application.LectureFacade;
import com.hhplus.clean.domain.Repository.LectureEnrollRepository;
import com.hhplus.clean.domain.Repository.LectureRepository;
import com.hhplus.clean.domain.Repository.LectureScheduleRepository;
import com.hhplus.clean.domain.Repository.UserRepository;
import com.hhplus.clean.domain.dto.LectureScheduleInfo;
import com.hhplus.clean.domain.dto.UserCommand;
import com.hhplus.clean.domain.entity.LectureEntity;
import com.hhplus.clean.domain.entity.LectureScheduleEntity;
import com.hhplus.clean.domain.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest

public class LectureEnrollIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private LectureScheduleRepository lectureScheduleRepository;

    @Autowired
    private LectureEnrollRepository lectureEnrollRepository;

    @Autowired
    private LectureFacade lectureFacade;

    @Test
    public void 동시에_동일한_특강_신청_40명_중_30명_성공() throws Exception {
        // Given: 수용 인원이 30명인 특강 일정 생성
        Long lectureScheduleId = createLectureScheduleWithCapacity(30);

        // 동시성을 위한 스레드 풀 및 동기화 도구 설정
        int numberOfRequests = 40; // 40명이 신청

        // Given: 40명의 사용자 생성
        List<UserCommand> users = createUsers(40);
        // 실제 사용자를 저장
        List<UserCommand> savedUsers = saveUsers(users);

        ExecutorService executorService = Executors.newFixedThreadPool(numberOfRequests);
        CountDownLatch latch = new CountDownLatch(numberOfRequests); // 동기화

        // When: 40명의 사용자가 동시에 신청
        for (int i = 0; i < numberOfRequests; i++) {
            int finalI = i;
            executorService.execute(() -> {
                try {
                    lectureFacade.enrollLecture(lectureScheduleId,savedUsers.get(finalI));
                } catch (Exception e) {
                    System.out.println("등록 실패: " + e.getMessage());
                } finally {
                    latch.countDown(); // 하나의 요청이 끝날 때마다 카운트 감소
                }
            });
        }

        // 모든 스레드가 작업을 마칠 때까지 대기
        latch.await();
        executorService.shutdown();

        // Then: 30명만 성공적으로 등록되었는지 확인
        LectureScheduleEntity updatedSchedule = lectureScheduleRepository.findById(lectureScheduleId).orElseThrow();
        assertThat(updatedSchedule.getCurrentCount()).isEqualTo(30); // 수용 인원이 30명인지 확인

    }

    private Long createLectureScheduleWithCapacity(int capacity) {
        LectureEntity lectureEntity = new LectureEntity();
        lectureEntity.setLectureName("test");
        LectureEntity saveLectureEntity = lectureRepository.save(lectureEntity);

        LectureScheduleEntity lectureSchedule = new LectureScheduleEntity();
        lectureSchedule.setLectureEntity(saveLectureEntity);
        lectureSchedule.setRegDate(LocalDate.of(2024, 10, 3));
        lectureSchedule.setCapacity(capacity);
        lectureSchedule.setCurrentCount(0);
        return lectureScheduleRepository.save(lectureSchedule).getId();
    }

    private List<UserCommand> createUsers(int numberOfUsers) {
        return IntStream.range(0, numberOfUsers)
                .mapToObj(i -> new UserCommand(i+1L,"test" + i))
                .collect(Collectors.toList());
    }

    private List<UserCommand> saveUsers(List<UserCommand> users) {
        return users.stream().map(userCommand -> {
            // UserEntity로 변환 후 저장
            UserEntity userEntity = userCommand.toEntity();
            Long savedId = userRepository.save(userEntity).getId();

            // 새로운 UserCommand 생성 (ID 포함)
            return new UserCommand(savedId, userCommand.userName());
        }).collect(Collectors.toList());
    }
}
