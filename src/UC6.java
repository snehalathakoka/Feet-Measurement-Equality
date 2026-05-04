public class UC6 {

    // Enum (same as UC5)
    enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12.0),
        YARD(3.0),
        CENTIMETER(0.393701 / 12.0);

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double toFeet(double value) {
            return value * toFeetFactor;
        }

        public double fromFeet(double feetValue) {
            return feetValue / toFeetFactor;
        }
    }

    // Quantity class
    static class Quantity {
        private final double value;
        private final LengthUnit unit;

        public Quantity(double value, LengthUnit unit) {
            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException("Invalid value");
            }
            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }
            this.value = value;
            this.unit = unit;
        }

        // Convert to feet
        private double toFeet() {
            return unit.toFeet(value);
        }

        // Convert to another unit
        public Quantity convertTo(LengthUnit targetUnit) {
            double feetValue = toFeet();
            double converted = targetUnit.fromFeet(feetValue);
            return new Quantity(converted, targetUnit);
        }

        // ✅ UC6: ADD method (instance)
        public Quantity add(Quantity other) {
            if (other == null) {
                throw new IllegalArgumentException("Other quantity cannot be null");
            }

            double sumFeet = this.toFeet() + other.toFeet();

            // return in unit of first operand
            double resultValue = this.unit.fromFeet(sumFeet);

            return new Quantity(resultValue, this.unit);
        }

        // ✅ Optional: static add (overloaded)
        public static Quantity add(Quantity q1, Quantity q2, LengthUnit targetUnit) {
            if (q1 == null || q2 == null || targetUnit == null) {
                throw new IllegalArgumentException("Invalid input");
            }

            double sumFeet = q1.toFeet() + q2.toFeet();
            double resultValue = targetUnit.fromFeet(sumFeet);

            return new Quantity(resultValue, targetUnit);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            Quantity other = (Quantity) obj;

            return Double.compare(this.toFeet(), other.toFeet()) == 0;
        }

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }

    public static void main(String[] args) {

        // Same unit
        Quantity q1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity q2 = new Quantity(2.0, LengthUnit.FEET);
        System.out.println("1 ft + 2 ft = " + q1.add(q2));

        // Cross unit
        Quantity q3 = new Quantity(1.0, LengthUnit.FEET);
        Quantity q4 = new Quantity(12.0, LengthUnit.INCH);
        System.out.println("1 ft + 12 inch = " + q3.add(q4));

        // Reverse (unit changes)
        System.out.println("12 inch + 1 ft = " + q4.add(q3));

        // Yard + Feet
        Quantity q5 = new Quantity(1.0, LengthUnit.YARD);
        Quantity q6 = new Quantity(3.0, LengthUnit.FEET);
        System.out.println("1 yard + 3 ft = " + q5.add(q6));

        // CM + Inch
        Quantity q7 = new Quantity(2.54, LengthUnit.CENTIMETER);
        Quantity q8 = new Quantity(1.0, LengthUnit.INCH);
        System.out.println("2.54 cm + 1 inch = " + q7.add(q8));

        // Zero case
        Quantity q9 = new Quantity(5.0, LengthUnit.FEET);
        Quantity q10 = new Quantity(0.0, LengthUnit.INCH);
        System.out.println("5 ft + 0 inch = " + q9.add(q10));

        // Negative case
        Quantity q11 = new Quantity(5.0, LengthUnit.FEET);
        Quantity q12 = new Quantity(-2.0, LengthUnit.FEET);
        System.out.println("5 ft + (-2 ft) = " + q11.add(q12));
    }
}