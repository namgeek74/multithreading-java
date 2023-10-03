package com.dsa;

import java.math.BigInteger;

public class ComplexCalculation {
    public BigInteger calculateResult(BigInteger base1, BigInteger power1, BigInteger base2, BigInteger power2) {
        BigInteger result;
        /*
            Calculate result = ( base1 ^ power1 ) + (base2 ^ power2).
            Where each calculation in (..) is calculated on a different thread
        */
        PowerCalculatingThread calc1 = new PowerCalculatingThread(base1, power1);
        PowerCalculatingThread calc2 = new PowerCalculatingThread(base2, power2);

        calc1.start();
        calc2.start();

        try {
            calc1.join();
            calc2.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }


        result = calc1.getResult().add(calc2.getResult());
        return result;
    }

    private static class PowerCalculatingThread extends Thread {
        private BigInteger result = BigInteger.ONE;
        private BigInteger base;
        private BigInteger power;

        public PowerCalculatingThread(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
            for (BigInteger i = BigInteger.ZERO;
                 i.compareTo(power) != 0;
                 i = i.add(BigInteger.ONE)) {
                result = result.multiply(base);
            }
        }

        public BigInteger getResult() {
            return result;
        }
    }
}
