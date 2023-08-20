package co.edu.uniquindio.clinicaVeterinaria.services;

import java.util.concurrent.CountDownLatch;

import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class CustomFxThread {
	private Service<Void> service;

	public static CustomFxThread crearHilo(Runnable accionEjecutable) {
		return new CustomFxThread(accionEjecutable);
	}

	private CustomFxThread(Runnable accionEjecutable) {
		service = new Service<Void>() {
			@Override
			protected Task<Void> createTask() {
				return new Task<Void>() {
					@Override
					protected Void call() throws Exception {
						final CountDownLatch latch = new CountDownLatch(1);
						try {
							accionEjecutable.run();
//							Platform.runLater(() -> accionEjecutable.run());
						} finally {
							latch.countDown();
						}

						latch.await();
						return null;
					}
				};
			}
		};
	}

	public void iniciarActividad() {
		service.start();
	}
}
