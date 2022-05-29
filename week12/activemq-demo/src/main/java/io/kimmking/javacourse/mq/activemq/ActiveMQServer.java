package io.kimmking.javacourse.mq.activemq;

import java.util.Scanner;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.broker.jmx.ManagementContext;


public class ActiveMQServer {

    public static void main(String[] args) {
        try {
            BrokerService brokerService = new BrokerService();
            brokerService.setBrokerName("admin");
            brokerService.addConnector("tcp://localhost:61616");
            brokerService.setManagementContext(new ManagementContext());
            brokerService.start();

            String inputStr = null;
            do {
                Scanner scanner = new Scanner(System.in);
                inputStr = scanner.nextLine();
            } while(!"end".equals(inputStr));
            brokerService.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
