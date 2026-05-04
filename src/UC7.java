public class UC7 {

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

        private double toFeet() {
            return unit.toFeet(value);
        }

        // UC6 method (kept)
        public Quantity add(Quantity other) {
            double sumFeet = this.toFeet() + other.toFeet();
            double result = this.unit.fromFeet(sumFeet);
            return new Quantity(result, this.unit);
        }

        // ✅ UC7: add with target unit
        public Quantity add(Quantity other, LengthUnit targetUnit) {
            if (other == null || targetUnit == null) {
                throw new IllegalArgumentException("Invalid input");
            }

            double sumFeet = this.toFeet() + other.toFeet();
            double result = targetUnit.fromFeet(sumFeet);

            return new Quantity(result, targetUnit);
        }

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }

    public static void main(String[] args) {

        Quantity q1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity q2 = new Quantity(12.0, LengthUnit.INCH);

        // Target = FEET
        System.out.println("Feet: " + q1.add(q2, LengthUnit.FEET));

        // Target = INCH
        System.out.println("Inch: " + q1.add(q2, LengthUnit.INCH));

        // Target = YARD
        System.out.println("Yard: " + q1.add(q2, LengthUnit.YARD));

        // Yard + Feet
        Quantity q3 = new Quantity(1.0, LengthUnit.YARD);
        Quantity q4 = new Quantity(3.0, LengthUnit.FEET);
        System.out.println("Yard result: " + q3.add(q4, LengthUnit.YARD));

        // CM + Inch
        Quantity q5 = new Quantity(2.54, LengthUnit.CENTIMETER);
        Quantity q6 = new Quantity(1.0, LengthUnit.INCH);
        System.out.println("CM result: " + q5.add(q6, LengthUnit.CENTIMETER));
    }
}