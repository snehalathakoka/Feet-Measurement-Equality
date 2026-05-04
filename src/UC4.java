public class UC4 {

    // Enum with extended units
    enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12.0),
        YARD(3.0),
        CENTIMETER(0.393701 / 12.0); // cm → inch → feet

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double toFeet(double value) {
            return value * toFeetFactor;
        }
    }

    // Generic Quantity class (same as UC3)
    static class Quantity {
        private final double value;
        private final LengthUnit unit;

        public Quantity(double value, LengthUnit unit) {
            this.value = value;
            this.unit = unit;
        }

        private double toFeet() {
            return unit.toFeet(value);
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) return true;

            if (obj == null || getClass() != obj.getClass()) return false;

            Quantity other = (Quantity) obj;

            return Double.compare(this.toFeet(), other.toFeet()) == 0;
        }
    }

    public static void main(String[] args) {

        // Yard ↔ Feet
        Quantity q1 = new Quantity(1.0, LengthUnit.YARD);
        Quantity q2 = new Quantity(3.0, LengthUnit.FEET);
        System.out.println("1 yard == 3 feet: " + q1.equals(q2));

        // Yard ↔ Inch
        Quantity q3 = new Quantity(1.0, LengthUnit.YARD);
        Quantity q4 = new Quantity(36.0, LengthUnit.INCH);
        System.out.println("1 yard == 36 inch: " + q3.equals(q4));

        // Yard ↔ Yard
        Quantity q5 = new Quantity(2.0, LengthUnit.YARD);
        Quantity q6 = new Quantity(2.0, LengthUnit.YARD);
        System.out.println("2 yard == 2 yard: " + q5.equals(q6));

        // CM ↔ CM
        Quantity q7 = new Quantity(2.0, LengthUnit.CENTIMETER);
        Quantity q8 = new Quantity(2.0, LengthUnit.CENTIMETER);
        System.out.println("2 cm == 2 cm: " + q7.equals(q8));

        // CM ↔ Inch
        Quantity q9 = new Quantity(1.0, LengthUnit.CENTIMETER);
        Quantity q10 = new Quantity(0.393701, LengthUnit.INCH);
        System.out.println("1 cm == 0.393701 inch: " + q9.equals(q10));
    }
}
