package ec.ups.edu.p3.hilo;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JLabel;

/**
 * 
 * @author fmont
 *
 */
public class Reloj implements Runnable {

	private JLabel label;
	private String hora;
	private String minutos;
	private String segundos;
	private String ampm;
	private Thread h1;

	public Reloj(JLabel label) {

		this.label = label;

		h1 = new Thread(this);
		h1.start();

	}

	public void run() {

		Thread ct = Thread.currentThread();
		while (ct == h1) {
			calcula();
			label.setText("     " + hora + ":" + minutos + ":" + segundos + " " + ampm);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}

	}

	public void calcula() {

		Calendar calendario = new GregorianCalendar();
		Date fechaHoraActual = new Date();

		calendario.setTime(fechaHoraActual);
		ampm = calendario.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";
		if (ampm.equals("AM")) {
			int h = calendario.get(Calendar.HOUR_OF_DAY) - 12;
			hora = h > 9 ? "" + h : "0" + h;
		}
		if (ampm.equals("PM")) {
			int h = calendario.get(Calendar.HOUR_OF_DAY) - 12;
			hora = h > 9 ? "" + h : "0" + h;
		}
		hora = calendario.get(Calendar.HOUR_OF_DAY) > 9 ? "" + calendario.get(Calendar.HOUR_OF_DAY)
				: "0" + calendario.get(Calendar.HOUR_OF_DAY);

		minutos = calendario.get(Calendar.MINUTE) > 9 ? "" + calendario.get(Calendar.MINUTE)
				: "0" + calendario.get(Calendar.MINUTE);
		segundos = calendario.get(Calendar.SECOND) > 9 ? "" + calendario.get(Calendar.SECOND)
				: "0" + calendario.get(Calendar.SECOND);
	}

	public String hora() {
		return hora + ":" + minutos + ":" + segundos + " " + ampm;
	}

}
