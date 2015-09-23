package lab.u2xd.socialminer.context.objects;

/**
 * Created by ysb on 2015-09-12.
 */
public class ContextLinker {
    private ContextData node1;
    private ContextData node2;
    private String attribute;
    private float distance;

    public ContextLinker(ContextData node1, ContextData node2, String attribute, float distance) {
        this.node1 = node1;
        this.node2 = node2;
        this.attribute = attribute;
        this.distance = distance;
    }

    /** 충격을 다른 노드에 전달, 인수는 거의 대부분 현재 객체로 넘겨주면 됨
     *
     * @param Impactor 충돌을 일으키는 객체
     */
    public void transmitImpact(ContextData Impactor) {

    }
}
