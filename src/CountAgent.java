import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;

import java.io.Serial;

public class CountAgent extends Agent {
    @Serial
    private static final long serialVersionUID = 1L;

    @Override
    public void setup() {
        Object[] args = getArguments();
        long tickPeriod = 500L;
        if (args != null && args.length > 0)
            tickPeriod = (Long) args[0];
        TickerBehaviour count = new Count(this, tickPeriod);
        addBehaviour(count);

    }
}
