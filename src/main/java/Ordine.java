import java.time.LocalDate;
import java.util.List;

public class Ordine {

    private Long id;
    private String stato;
    private LocalDate dataOrdine;
    private LocalDate dataConsegna;

    private List<Prodotto> prodotti;
    private Cliente cliente;

    public Ordine(Long id, String stato, LocalDate dataOrdine, LocalDate dataConsegna, List<Prodotto> prodotti, Cliente cliente) {
        this.id = id;
        this.stato = stato;
        this.dataOrdine = dataOrdine;
        this.dataConsegna = dataConsegna;
        this.prodotti = prodotti;
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public LocalDate getDataOrdine() {
        return dataOrdine;
    }

    public void setDataOrdine(LocalDate dataOrdine) {
        this.dataOrdine = dataOrdine;
    }

    public LocalDate getDataConsegna() {
        return dataConsegna;
    }

    public void setDataConsegna(LocalDate dataConsegna) {
        this.dataConsegna = dataConsegna;
    }

    public List<Prodotto> getProdotti() {
        return prodotti;
    }

    public void setProdotti(List<Prodotto> prodotti) {
        this.prodotti = prodotti;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Ordine{" +
                "id=" + id +
                ", stato='" + stato + '\'' +
                ", dataOrdine=" + dataOrdine +
                ", dataConsegna=" + dataConsegna +
                ", prodotti=" + prodotti +
                ", cliente=" + cliente +
                '}';
    }
}
