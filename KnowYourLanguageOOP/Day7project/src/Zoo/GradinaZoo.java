package Zoo;

import Zoo.Animale.AnimalZooRar;
import Zoo.Personal.IngrijitorZoo;

import java.util.Date;

/**
 * Created by Alexandra.Gaman on 7/4/2017.
 */
public final class GradinaZoo {
    private final String denumireGradinaZoo;
    private final Date dataDeschideriiGradinii;
    private final AnimalZooRar animalrar;
    private final IngrijitorZoo angajatulLunii;

    public GradinaZoo(String denumireGradinaZoo, Date dataDeschideriiGradinii, AnimalZooRar animalrar, IngrijitorZoo angajatulLunii) {
        this.denumireGradinaZoo = denumireGradinaZoo;
        this.dataDeschideriiGradinii = dataDeschideriiGradinii;
        this.animalrar = animalrar;
        this.angajatulLunii = angajatulLunii;
    }

    public String getDenumireGradinaZoo(){
        return denumireGradinaZoo;
    }

    public Date getDataDeschideriiGradinii(){
        Date data = (Date) dataDeschideriiGradinii.clone();
        return data;
    }

    public AnimalZooRar getAnimalrar(){
        AnimalZooRar animal = new AnimalZooRar();
        animal.setNume(animalrar.getNume());
        animal.setNumeTaraDeOrigine(animalrar.getNumeTaraDeOrigine());
        return animal;
    }

    public IngrijitorZoo getAngajatulLunii(){
        IngrijitorZoo ingrijitor = new IngrijitorZoo();
        ingrijitor.setSumaBonus(angajatulLunii.getSumaBonus());
        return ingrijitor;
    }

}
