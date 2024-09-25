package io.hhplus.tdd.point.service;

import io.hhplus.tdd.point.record.PointHistory;
import io.hhplus.tdd.point.record.UserPoint;
import io.hhplus.tdd.point.repository.PointHistoryRepository;
import io.hhplus.tdd.point.repository.UserPointRepository;

import java.util.List;


public class PointServiceImpl implements PointService {

    final UserPointRepository userPointRepository;
    final PointHistoryRepository pointHistoryRepository;

    public final String ID_VALIDATOR_ERROR_MESSAGE = "잘못된 ID 입니다";
    public final String AMOUNT_VALIDATOR_ERROR_MESSAGE = "금액은 0원 이상이어야 합니다";


    /** 생성자 의존성 주입
     * @param userPointRepository UserPointRepository
     * @param pointHistoryRepository PointHistoryRepository
     */
    public PointServiceImpl(UserPointRepository userPointRepository, PointHistoryRepository pointHistoryRepository) {
        this.userPointRepository = userPointRepository;
        this.pointHistoryRepository = pointHistoryRepository;
    }


    /** 유저별 포인트 조회
     * @param id long
     * @return UserPoint
     */
    @Override
    public UserPoint getUserPointById(long id) {

        this.idValidator(id);

        return this.userPointRepository.findById(id);
    }

    /** 포인트 내역 조회
     * @param id long
     * @return PointHistory[]
     */
    @Override
    public List<PointHistory> getUserPointHistoryListById(long id) {
        this.idValidator(id);

        return this.pointHistoryRepository.findAllById(id);
    }



    public void idValidator(long id){
        if(id <=0){
            throw new IllegalArgumentException(this.ID_VALIDATOR_ERROR_MESSAGE);
        }
    }

    public void amountValidator(long amount){
        if(amount <=0){
            throw new IllegalArgumentException(this.AMOUNT_VALIDATOR_ERROR_MESSAGE);
        }
    }
}
