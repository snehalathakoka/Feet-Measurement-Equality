public class UC8 {

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
            return unit.toBase(value); // delegation
        }

        public Quantity convertTo(LengthUnit targetUnit) {
            double base = toFeet();
            double result = targetUnit.fromBase(base);
            return new Quantity(result, targetUnit);
        }

        // UC7 method retained
        public Quantity add(Quantity other, LengthUnit targetUnit) {
            if (other == null || targetUnit == null) {
                throw new IllegalArgumentException("Invalid input");
            }

            double sum = this.toFeet() + other.toFeet();
            double result = targetUnit.fromBase(sum);

            return new Quantity(result, targetUnit);
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

        Quantity q1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity q2 = new Quantity(12.0, LengthUnit.INCH);

        // Conversion
        System.out.println("Convert: " + q1.convertTo(LengthUnit.INCH));

        // Addition
        System.out.println("Add: " + q1.add(q2, LengthUnit.FEET));

        // Equality
        Quantity q3 = new Quantity(36.0, LengthUnit.INCH);
        Quantity q4 = new Quantity(1.0, LengthUnit.YARD);
        System.out.println("Equals: " + q3.equals(q4));

        // Direct enum usage
        System.out.println("12 inch in feet: " +
                LengthUnit.INCH.toBase(12.0));
    }
}