import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;

public class Count extends TickerBehaviour {

    public Integer num;

    public Count(Agent a, long period) {
        super(a, period);
        num = 1;
    }

    @Override
    protected void onTick() {
        if (num == 100) {
            this.getAgent().doDelete();
        }
        System.out.printf("Agent %s counted until %d\n", this.getAgent().getName(), num);
        num += 1;
    }

}
