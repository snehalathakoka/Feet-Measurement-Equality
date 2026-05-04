public class UC9 {

    static class QuantityWeight {
        private final double value;
        private final WeightUnit unit;

        public QuantityWeight(double value, WeightUnit unit) {
            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException("Invalid value");
            }
            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }
            this.value = value;
            this.unit = unit;
        }

        private double toKg() {
            return unit.toBase(value);
        }

        // ✅ Convert
        public QuantityWeight convertTo(WeightUnit targetUnit) {
            double base = toKg();
            double result = targetUnit.fromBase(base);
            return new QuantityWeight(result, targetUnit);
        }

        // ✅ Add (UC6 style)
        public QuantityWeight add(QuantityWeight other) {
            double sum = this.toKg() + other.toKg();
            double result = this.unit.fromBase(sum);
            return new QuantityWeight(result, this.unit);
        }

        // ✅ Add with target (UC7 style)
        public QuantityWeight add(QuantityWeight other, WeightUnit targetUnit) {
            if (other == null || targetUnit == null) {
                throw new IllegalArgumentException("Invalid input");
            }

            double sum = this.toKg() + other.toKg();
            double result = targetUnit.fromBase(sum);

            return new QuantityWeight(result, targetUnit);
        }

        // ✅ Equality
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            QuantityWeight other = (QuantityWeight) obj;

            return Double.compare(this.toKg(), other.toKg()) == 0;
        }

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }

    public static void main(String[] args) {

        // Equality
        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(1000.0, WeightUnit.GRAM);
        System.out.println("Equal: " + w1.equals(w2));

        // Conversion
        System.out.println("Convert: " + w1.convertTo(WeightUnit.GRAM));

        // Addition
        QuantityWeight w3 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight w4 = new QuantityWeight(2.0, WeightUnit.POUND);
        System.out.println("Add: " + w3.add(w4));

        // Addition with target
        System.out.println("Add in grams: " + w3.add(w4, WeightUnit.GRAM));
    }
}