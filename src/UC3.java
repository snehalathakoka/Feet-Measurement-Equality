public class UC3 {

    // Step 1: Enum for units (conversion to FEET as base)
    enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12.0);

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double toFeet(double value) {
            return value * toFeetFactor;
        }
    }

    // Step 2: Generic Quantity class (DRY)
    static class Quantity {
        private final double value;
        private final LengthUnit unit;

        public Quantity(double value, LengthUnit unit) {
            this.value = value;
            this.unit = unit;
        }

        // Convert any unit to feet
        private double toFeet() {
            return unit.toFeet(value);
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) return true;

            if (obj == null || getClass() != obj.getClass()) return false;

            Quantity other = (Quantity) obj;

            // Compare after converting to common base (feet)
            return Double.compare(this.toFeet(), other.toFeet()) == 0;
        }
    }

    // Main (test UC3)
    public static void main(String[] args) {

        // Cross-unit equality
        Quantity q1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity q2 = new Quantity(12.0, LengthUnit.INCH);

        System.out.println("UC3 - 1 ft == 12 inch: " + q1.equals(q2));

        // Same-unit equality
        Quantity q3 = new Quantity(1.0, LengthUnit.INCH);
        Quantity q4 = new Quantity(1.0, LengthUnit.INCH);

        System.out.println("UC3 - 1 inch == 1 inch: " + q3.equals(q4));

        // Different values
        Quantity q5 = new Quantity(2.0, LengthUnit.FEET);

        System.out.println("UC3 - 1 ft == 2 ft: " + q1.equals(q5));
    }
}