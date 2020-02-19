package ec.ups.edu.p3.aplicacion;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author fmont
 *
 */
public class CalculoTiempo {
	static long milisegundos_dia = 24 * 60 * 60 * 1000;

	public String calcularTiempo(String f1, String f2) {

		String total = "";

		Date fecha1 = null;
		Date fecha2 = null;

		try {
			fecha1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(f1);
			fecha2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(f2);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Calendar calFechaInicial = Calendar.getInstance();
		Calendar calFechaFinal = Calendar.getInstance();

		calFechaInicial.setTime(fecha1);
		calFechaFinal.setTime(fecha2);

		long horas = diferenciaHorasDias(calFechaInicial, calFechaFinal)
				+ diferenciaHoras(calFechaInicial, calFechaFinal);
		long minutos = diferenciaMinutos(calFechaInicial, calFechaFinal);

		if (minutos < 0)
			total = (horas - 1) + "\t" + (minutos + 60);
		else
			total = horas + "\t" + minutos;

		return total;

	}

	public static long diferenciaHorasDias(Calendar fechaInicial, Calendar fechaFinal) {
		long diferenciaHoras = 0;
		diferenciaHoras = (fechaFinal.getTimeInMillis() - fechaInicial.getTimeInMillis()) / milisegundos_dia;
		if (diferenciaHoras > 0) {
			diferenciaHoras *= 24;
		}
		return diferenciaHoras;
	}

	public static long diferenciaHoras(Calendar fechaInicial, Calendar fechaFinal) {
		long diferenciaHoras = 0;
		long horaIN = fechaFinal.get(Calendar.HOUR_OF_DAY) == 0 ? 24 : fechaFinal.get(Calendar.HOUR_OF_DAY);
		long horaOUT = fechaInicial.get(Calendar.HOUR_OF_DAY);
		if (horaOUT < horaIN) {
			diferenciaHoras = horaIN - horaOUT;
		} else {
			diferenciaHoras = horaOUT - horaIN;
		}
		return diferenciaHoras;
	}

	public static long diferenciaMinutos(Calendar fechaInicial, Calendar fechaFinal) {
		long diferenciaHoras = 0;
		diferenciaHoras = (fechaFinal.get(Calendar.MINUTE) - fechaInicial.get(Calendar.MINUTE));
		return diferenciaHoras;
	}

}