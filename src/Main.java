import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

import java.util.Random;

public class Main {
    public static void main(String[] args) throws RuntimeException {
        jade.core.Runtime rt = jade.core.Runtime.instance();
        rt.setCloseVM(true);

        String containerName = "Container1";
        Profile p = new ProfileImpl();
        AgentContainer mc = rt.createMainContainer(p);
        ProfileImpl pContainer = new ProfileImpl(false);

        pContainer.setParameter(Profile.CONTAINER_NAME, containerName);
        System.out.println("Launching container " + pContainer);
        ContainerController containerRef1 = rt.createAgentContainer(pContainer);
        //create container2
        containerName = "Container2";
        pContainer = new ProfileImpl(false);
        pContainer.setParameter(Profile.CONTAINER_NAME, containerName);
        System.out.println("Launching container " + pContainer);
        ContainerController containerRef2 = rt.createAgentContainer(pContainer);
        Random rd = new Random(); // creating Random object
        Long[] randomLongs = new Long[4];
        for (int i = 0; i < randomLongs.length; i++) {
            randomLongs[i] = rd.nextLong(500L, 5000L); // storing random integers in an array
        }
        try {
            AgentController rma = mc.createNewAgent("rma", "jade.tools.rma.rma", null);
            rma.start();
            AgentController ac1Counter1 = containerRef1.createNewAgent("ac1counter1", "CountAgent", new Object[]{randomLongs[0]});
            AgentController ac1Counter2 = containerRef1.createNewAgent("ac1counter2", "CountAgent", new Object[]{randomLongs[1]});
            AgentController ac2Counter1 = containerRef2.createNewAgent("ac2counter1", "CountAgent", new Object[]{randomLongs[2]});
            AgentController ac2Counter2 = containerRef2.createNewAgent("ac2counter2", "CountAgent", new Object[]{randomLongs[3]});
            ac1Counter1.start();
            ac1Counter2.start();
            ac2Counter1.start();
            ac2Counter2.start();

        } catch (StaleProxyException e) {
            throw new RuntimeException(e);
        }
    }
}