/**
 * Class describing a timer
 * @author Dmitriy Stepanov
 */
public class Timer {
    double time;
    double limit;
    Action action;
    boolean repeat;
    boolean endgame;

    /**
     * Constructor - creating a new timer
     * @param limit - limit the time
     * @param repeat - repeat option
     * @param action - action
     * @see Timer#Timer(double,boolean,Action)
     */
    public Timer(double limit, boolean repeat, Action action) {
        this.limit = limit;
        this.action = action;
        this.repeat = repeat;
    }

    public void tickTimer(double dt) {
        if(endgame) return;
        time += dt;
        if(time > limit) {
            action.execute();
            if(repeat) {
                time -= limit;
            } else {
                endgame = true;
            }
        }
    }
}