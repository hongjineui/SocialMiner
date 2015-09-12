package lab.u2xd.socialminer.context.miner.callback;

/**
 * Created by yim on 2015-09-07.
 */
public interface Queryable {
    /** 데이터 쿼리를 받아 처리를 완료했을 때 호출.
     *
     * @param sender 쿼리를 요청받은 객체
     */
    void receiveData(Object sender);
    //Object 형식으로 돌려주는 이유는 쿼리한 객체가 쿼리를 요청한 객체의 정보를 가지고 있어야만 데이터를 받을 수 있기 때문.
}
