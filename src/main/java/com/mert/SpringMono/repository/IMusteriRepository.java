package com.mert.SpringMono.repository;

import com.mert.SpringMono.repository.entity.Musteri;
import com.mert.SpringMono.repository.view.VwMusteri;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/*
* Spring BeanFactory ile oluşturacağı nesneleri belirlemek için belli anotasyonları arar.
* Bunlardan 1.si veritabanı işlemleri için
* @Repository dir.
 */
@Repository
public interface IMusteriRepository extends JpaRepository<Musteri,Long> {


    /**
     * !! DİKKAT !!
     * Spring in devraldığı repository interface lerinde, method tanımlamaları için özel
     * keyword ler kullanılmaktadır. Bunlar üzerinden sorgular oluşturulur.
     * 1- ReturnType -> Musteri, List<Musteri>, Boolean, Integer v.s.
     * 2- find, kelimesini kullanıyoruz.
     * 3- By, ne için arama yapılacağını belilenmesi işaretlenmesi için kullanılır.
     * 4- Entity Property[entity-> değişkenin adı], üzerinde çalıştığınız repository nin kullanmakta
     * olduğu entity sınıfından bir değişkenin birebir adını yazmalısınız.
     * DİKKAT !!!!!!!!!!!! burada varlık adı yazılırken büyük harf ile başlanır.yine dikkat edilmesi
     * gereken bir husus eğer değişken adı camemlcase şeklinde (musteriAdSoyad) şeklinde
     * yazılmış ise buna dikkat ederek yazılmalıdır.
     * 5- Method için gerekli parametreler değişken türüne göre eklenir.
     * !!! DİKKAT !!!! burada değişken adı önemli değil, değişken türü önemlidir.
     */
    List<Musteri> findByAd(String burayabirdegeryazmamgerekli);
    /**
     * select * from tblmusteri where ad=? and adres=?
     * @param bisey
     * @param baskabisey
     * @return
     */
    List<Musteri> findAllByAdAndAdres(String bisey,String baskabisey);

    /**
     * HAngi yaş grubunun hangi ürüleri daha fazla satın aldığını merak ediyorsunuz.
     * örn: 40 yaş üzeri müşterilerin listesi.
     *
     */
    List<Musteri> findAllByYasGreaterThan(Integer yas); // yas>?
    List<Musteri> findAllByYasGreaterThanEqual(Integer yas); // yas>=?

    /**
     * Belli bir harfin ya da kelimenin aranması  LIKE, ILIKE
     */
    List<Musteri> findAllByAdLike(String ad);

    /**
     *  select * from tblmusteri ad like '?%'
     */

    List<Musteri> findAllByAdStartingWith(String ad);

    /**
     * select * from tblmusteri where ad=? and adres=? and telefon=?
     * @param ad
     * @param adres
     * @param telefon
     * @return
     */
    Musteri findByAdAndAdresAndTelefon(String ad,String adres,String telefon);

    /**
     * select * from tblmusteri where yas=5000
     * Eğer sonuç yok ise,null dönebilir böyle durumlarda null kontrol yapmak çokta doğru değildir.
     * Bunun yerine Optional kullanmak doğru olacaktır.
     */
    Optional<Musteri> findOptionalByTelefon(String telefon); //Eğer null ise empty
    Optional<List<Musteri>> findAllOptionalByAdres(String adres);

    /**
     * Order
     * -> ASC       A....Z  0...9
     * select * from tblmusteri order by yas
     * -> DESC      Z....A  9...0
     */

    List<Musteri> findByOrderByYas();  //yaşına göre kücükten büyüğe dogru sıralı liste verir.
    List<Musteri> findByOrderByYasDesc(); //yaşına göre büyükten kücüge
    Musteri findTopByOrderByYasDesc(); // büyükten kücüge sıralar ilk kaydı getirir(EnYaşlı).
    Musteri findTopOptionalByOrderByYasDesc(); // büyükten kücüge sıralar ilk kaydı getirir(EnYaşlı).Null kontrolü var

    /**
     * LIMIT kullanımı
     */
    List<Musteri> findTop5ByOrderByYas(); //en geç 5 üyemiz

    /**
     * select * from tblmusteri where yas>18 and yas<40 gençlerde satış oranı
     */
    List<Musteri> findAllByYasBetween(Integer start,Integer end); //yas>=? and yas<=?
    List<Musteri> findAllByAdresAndYasBetween(String adres,Integer start,Integer end);

    /**
     * Is, true-false
     */
    List<Musteri> findAllByState(boolean state); //Aktif pasif kayıtları verir.
    List<Musteri> findAllByStateTrue(); //true - aktif olan kayıtlar
    List<Musteri> findAllByStateFalse(); //true - pasif olan kayıtlar

    /**
     * Ahmet -> Tbl("ahmet) false
     */
//    List<Musteri> findAllByAdAndAdres(String ad,String adres);
    List<Musteri> findAllByAdIgnoreCaseAndAdresIgnoreCase(String ad,String adres);

    /**
     * Belirli bir isim listesini çekelim
     * List<String> adListesi = {"Ahmet","Ayşe","Canan"}
     */
    List<Musteri> findAllByAdIn(List<String> adListesi);

    /**
     * JPQL yada HQL veya NATIVESQL kullanılabilir
     */

    /**
     * HQL Kullanımı
     * JPQL Kullanımı
     */
    @Query("select m from Musteri m where m.adres=?1") //Alttaki string bu soru işaretine gelicek 1? 1. 2? 2.
    List<Musteri> senBulTumunuMusterilerinAdresineGore(String adresOlabilir);
    @Query("select m from Musteri m where m.ad=?3 and m.adres=?1 and m.telefon=?2")
    Musteri musteriyiBul(String adres,String telefon,String ad);


    /**
     * NATIVESQL Kullanımı
     */
    @Query(value = "select * from Musteri where ad=?1",nativeQuery = true)
    List<Musteri> bulAdinaGore(String ad);

    /**
     * Sorgu parametlerinin kullanılarak tanımlanması ve method içinden çekilmesi
     */
    @Query("select m from Musteri m where m.ad = :ad and m.adres = :adres")
    List<Musteri> findAdAndAdres(@Param("ad") String ad,
                                 @Param("adres") String adres);

    @Query("select COUNT(m)>0 from Musteri m where m.tckimlik=?1")
    Boolean musteriVarmi(String tckimlik);

    @Query("select new com.mert.SpringMono.repository.view.VwMusteri(m.id,m.ad) from Musteri m")
    List<VwMusteri> findAllViewMusteri();

}
