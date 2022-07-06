package org.demo.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Publisher {

	public static void main(String[] args) {
		// Create a ActiveMQ Connection Factory by sending the username,password and the
		// url

		ConnectionFactory factory = new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");
		Connection connection = null;
		Session session = null;
		try {
			// Get the connection from the factory
			connection = factory.createConnection();
			// Create a session from the connection
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			// Create a queue.A new queue is not created if the queue already exists
			Destination queue = session.createQueue("demo-queue");
			// Creating a text message to send to the queue
			TextMessage message = session.createTextMessage("Hello World");
			// Creating a Message Producer
			MessageProducer producer = session.createProducer(queue);
			producer.send(message);
			System.out.println("Message sent succesfully");
		} catch (JMSException e) {
			e.printStackTrace();
		} finally {

			try {
				session.close();
				connection.close();
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}

}
