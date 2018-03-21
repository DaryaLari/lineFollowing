package lineFollowing;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;

public class LineFollowing {


    RegulatedMotor leftMotor = new EV3LargeRegulatedMotor(LocalEV3.get().getPort("B"));
    RegulatedMotor rightMotor = new EV3LargeRegulatedMotor(LocalEV3.get().getPort("C"));

    EV3ColorSensor leftSensor = new EV3ColorSensor(LocalEV3.get().getPort("S2"));
    EV3ColorSensor rightSensor = new EV3ColorSensor(LocalEV3.get().getPort("S3"));

    SampleProvider leftSample = leftSensor.getRedMode();
    SampleProvider rightSample = rightSensor.getRedMode();

    float[] sampleResults = new float[2];


    public void followLine(){
        int speed = 170;
        final double kp = 150, kd = 30, ki = 0.1;
        double correction = 0;
        double prevError = 0, error = 0, integral = 0, derivative = 0;
        while(true) {
            leftSample.fetchSample(sampleResults, 0);
            rightSample.fetchSample(sampleResults, 1);
//            System.out.println(sampleResults[0] + " " + sampleResults[1]);

            error = sampleResults[0] - sampleResults[1];
            integral += error;
            derivative = error - prevError;
            prevError = error;

            correction = kp * error + ki * integral + kd * derivative;

            leftMotor.synchronizeWith(new RegulatedMotor[]{rightMotor});
            leftMotor.startSynchronization();
            leftMotor.setSpeed(speed - (int)correction);
            rightMotor.setSpeed(speed + (int)correction);
            leftMotor.forward();
            rightMotor.forward();
            leftMotor.endSynchronization();



//            System.out.println("LS: " + String.format("%.2f", sampleResults[0]) +
//                    "\nRS: " + String.format("%.2f", sampleResults[1]) +
//                    "\nP: " + String.format("%.2f", error) +
//                    "\nI: " + String.format("%.2f", integral) +
//                    "\nD: " + String.format("%.2f", derivative));

//            Delay.msDelay(100);

        }
    }

}
