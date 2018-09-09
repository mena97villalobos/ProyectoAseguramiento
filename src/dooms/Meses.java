package dooms;

public enum Meses {
    Enero(1) , Febrero(2), Marzo(3), Abril(4), Mayo(5), Junio(6), Julio(7), Agosto(8), Setiembre(9), Octubre(10), Noviembre(11),Diciembre(12);

    private final int id;

    Meses(int id) { this.id = id; }

    public static Meses getById(int id) {
        for (Meses meses : Meses.values()) {
            if (meses.id == id)
                return meses;
        }
        return Enero;
    }

}
