package wrk;


import com.phidget22.PhidgetException;
import com.phidget22.TemperatureSensor;
import com.phidget22.TemperatureSensorTemperatureChangeEvent;
import com.phidget22.TemperatureSensorTemperatureChangeListener;

/**
 * @author raposoesilvac
 * @version 1.0
 * @created 11-nov.-2022 11:44:15
 */
public class WrkPhidget {

		private final static int VINT_SERIALID = 636313;
	public ItfWrkPhidget refWrk;

	public WrkPhidget(){
		try {
			TemperatureSensor temperatureSensor = new TemperatureSensor();

			temperatureSensor.setDeviceSerialNumber(VINT_SERIALID);
			temperatureSensor.setChannel(1);

			temperatureSensor.addTemperatureChangeListener(new TemperatureSensorTemperatureChangeListener() {
				@Override
				public void onTemperatureChange(TemperatureSensorTemperatureChangeEvent temperatureSensorTemperatureChangeEvent) {
					refWrk.receiveTemperature(temperatureSensorTemperatureChangeEvent.getTemperature());
				}
			});
			temperatureSensor.open();

		} catch (PhidgetException e) {

		}
	}

	public void finalize() throws Throwable {

	}

}//end WrkPhidget