--
-- PostgreSQL database dump
--

-- Dumped from database version 9.4.5
-- Dumped by pg_dump version 9.4.5
-- Started on 2016-11-10 23:51:41 EET
--  psql -U postgres -h localhost -d aracKirala -f "/home/seray/Desktop/aracKirala.sql"

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 2081 (class 1262 OID 17323)
-- Name: company2; Type: DATABASE; Schema: -; Owner: postgres
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 6 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

--
-- TOC entry 2082 (class 0 OID 0)
-- Dependencies: 6
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


--
-- TOC entry 179 (class 3079 OID 11895)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2084 (class 0 OID 0)
-- Dependencies: 179
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;



-- -------------------------------------------------------------------------------
-- -------------------------------------------------------------------------------
-- -------------------------------------------------------------------------------
-- -------------------------------------------------------------------------------
-- -------------------------------------------------------------------------------
-- -------------------------------------------------------------------------------




CREATE TABLE musteri (
   
    musteriID integer NOT NULL,
    telefon character varying(50),
    adres character varying(50),
    mail character varying(50)

   
);


ALTER TABLE musteri OWNER TO postgres;

   
ALTER TABLE ONLY musteri
    ADD CONSTRAINT xpkmusteri PRIMARY KEY (musteriID);


    

CREATE TABLE kisi (
   
    kmusteriID integer NOT NULL,
    isim character varying(30),
    soyisim character varying(30),
    cinsiyet character varying(10),
    yas integer,
    TC character varying(10),
    ehliyetSinifi character varying(10)
    

   
);


ALTER TABLE kisi OWNER TO postgres;



ALTER TABLE ONLY kisi
    ADD CONSTRAINT xpkkisi PRIMARY KEY (kmusteriID);

    
   
CREATE TABLE sirket (
   
    smusteriID integer NOT NULL,
    sirketIsmi character varying(30)

);


ALTER TABLE sirket OWNER TO postgres;



ALTER TABLE ONLY sirket
    ADD CONSTRAINT xpksirket PRIMARY KEY (smusteriID);

    

   
CREATE TABLE arac (
   
    aracID integer NOT NULL,
    durumID integer,
    plakaNo character varying(30),
    vites character varying(30),
    marka character varying(30),
    model character varying(30),
    fiyat character varying(30),
    kaskoBilgisi character varying(30),
    km character varying(30),
    renk character varying(30),
    yakitTuru character varying(30),
    motorGucu character varying(30)

);


ALTER TABLE arac OWNER TO postgres;


ALTER TABLE ONLY arac
    ADD CONSTRAINT xpkarac PRIMARY KEY (aracID);  
    
    
CREATE TABLE kiralananArac (
   
    kiralananAracID integer NOT NULL,
    kiralayanID integer NOT NULL
  

);


ALTER TABLE kiralananArac OWNER TO postgres;


ALTER TABLE ONLY kiralananArac
    ADD CONSTRAINT xpkkiralananArac PRIMARY KEY (kiralananAracID);  
    
    
CREATE TABLE durum (
   
    aracDurumID integer NOT NULL,
    durumBilgisi character varying(30)

);




ALTER TABLE durum OWNER TO postgres;


ALTER TABLE ONLY durum
    ADD CONSTRAINT xpkdurum PRIMARY KEY (aracDurumID);  
    

    
CREATE TABLE araba (
   
    arabaID integer NOT NULL,
    kapiSayisi character varying(30)


);


ALTER TABLE araba OWNER TO postgres;


ALTER TABLE ONLY araba
    ADD CONSTRAINT xpkaraba PRIMARY KEY (arabaID);  
    

    
    
CREATE TABLE ismakinesi (
   
    ismakinesiID integer NOT NULL,
    tonaj character varying(30)


);


ALTER TABLE ismakinesi OWNER TO postgres;


ALTER TABLE ONLY ismakinesi
    ADD CONSTRAINT xpkismakinesi PRIMARY KEY (ismakinesiID);  
    

 
 
 
CREATE TABLE islem (
   
    islemID integer NOT NULL,
    islemTutari character varying(30)

);


ALTER TABLE islem OWNER TO postgres;


ALTER TABLE ONLY islem
    ADD CONSTRAINT xpkislem PRIMARY KEY (islemID);      
    

    
    
    
CREATE TABLE teslimat (
   
    TeslimatislemID integer NOT NULL,
    teslimatSaati character varying(30),
    teslimatTarihi character varying(30),
    tAracID integer,
    tMusteriID integer


);


ALTER TABLE teslimat OWNER TO postgres;


ALTER TABLE ONLY teslimat
    ADD CONSTRAINT xpkteslimat PRIMARY KEY (TeslimatislemID);
    

    
    
    
CREATE TABLE sozlesme (
   
    SozlesmeislemID integer NOT NULL,
    sozlesmeNo integer,
    temsilciAdi character varying(30),
    sMusteriID integer


);


ALTER TABLE sozlesme OWNER TO postgres;


ALTER TABLE ONLY sozlesme
    ADD CONSTRAINT xpksozlesme PRIMARY KEY (SozlesmeislemID);
    
 
  
    
CREATE TABLE bakim (
   
    BakimislemID integer NOT NULL,
    bakimYapilanYer character varying(30),
    bakimdaYapilanIslem character varying(30),
    bAracID integer

);


ALTER TABLE bakim OWNER TO postgres;


ALTER TABLE ONLY bakim
    ADD CONSTRAINT xpkbakim PRIMARY KEY (BakimislemID);
    

    
CREATE TABLE odeme (
   
    OdemeislemID integer NOT NULL,
    odemeSekli character varying(30),
    odemeTutari character varying(30),
    oMusteriID integer

    
);


ALTER TABLE odeme OWNER TO postgres;


ALTER TABLE ONLY odeme
    ADD CONSTRAINT xpkodeme PRIMARY KEY (OdemeislemID);
    

    
    
    
    
-- -------------------------------------------------------------------------------
-- -------------------------------------------------------------------------------    
-- -------------------------------------------------------------------------------
-- -------------------------------------------------------------------------------
-- -------------------------------------------------------------------------------
-- -------------------------------------------------------------------------------

INSERT INTO durum VALUES (1,'uygun');
INSERT INTO durum VALUES (2,'kirada');
INSERT INTO durum VALUES (3,'arizali');
INSERT INTO durum VALUES (4,'bakimda');
INSERT INTO durum VALUES (5,'hasarli');

INSERT INTO musteri VALUES (1,'05391231212','Dikmen Caddesi No:21 ANKARA ','ayse@mail');
INSERT INTO musteri VALUES (2,'05331334556','TOBB Universitesi, Sogutozu, ANKARA','cemal@mail');
INSERT INTO musteri VALUES (3,'05351234578','Esat Caddesi No:45 ANKARA','beril@mail');
INSERT INTO musteri VALUES (4,'03124444989','Gaziosmanpasa Koroglu Caddesi No:56 ANKARA','sirket@mail');
INSERT INTO musteri VALUES (5,'03124469633','Sevgi Caddesi No:34 ANKARA','asli@mail');
INSERT INTO musteri VALUES (6,'03124469678','Ataturk Bulvarı Yasemen Apt. ANKARA','sirket2@mail');
INSERT INTO musteri VALUES (7,'03127899678','Cinnah Caddesi 5/6 ANKARA','sirket3@mail');
INSERT INTO musteri VALUES (8,'03112345678','Turan Gunes Caddesi No:8 ANKARA','sirket4@mail');


INSERT INTO kisi VALUES (1,'ayse','simsek','kadin',21,'1231223432','B sinif');
INSERT INTO kisi VALUES (2,'cemal','derin','erkek',45,'9347387444','B sinif');
INSERT INTO kisi VALUES (3,'beril','aksu','kadin',33,'2345233432','A sinif');
INSERT INTO kisi VALUES (5,'asli','cakir','kadin',24,'2123453432','B sinif');

INSERT INTO sirket VALUES(4,'Beser Holding');
INSERT INTO sirket VALUES(6,'Dunya Holding');
INSERT INTO sirket VALUES(7,'Kurt Holding');
INSERT INTO sirket VALUES(8,'Kuzu Holding');


INSERT INTO arac VALUES (1,2,'06 MBK 06','duz','fiat','marea','30.000','kasko var','100.000','gri','benzin','12');
INSERT INTO arac VALUES (2,2,'06 ASD 78','otomatik','honda','civic','50.000','kasko var','200.000','beyaz','benzin','15');
INSERT INTO arac VALUES (3,2,'06 CVB 126','duz','audi','a4','90.000','kasko var','20.000','siyah','dizel','15');
INSERT INTO arac VALUES (4,4,'06 KLM 61','duz','kamyon','XX','20.000','kasko var','200.000','siyah','dizel','8');
INSERT INTO arac VALUES (5,1,'06 AS 67','otomatik','honda','CRV','120.000','kasko var','50.000','siyah','dizel','15');
INSERT INTO arac VALUES (6,2,'06 QW 06','duz','tir','XX','20.000','kasko var','500.000','siyah','benzin','5');
INSERT INTO arac VALUES (7,2,'06 ER 56','duz','kamyon','XXXX','20.000','kasko var','120.000','siyah','benzin','4');
INSERT INTO arac VALUES (8,4,'06 HS 56','duz','kamyon','XXXXX','50.000','kasko var','20.000','kirmizi','benzin','9');

   
INSERT INTO araba VALUES(1,'4');
INSERT INTO araba VALUES(2,'4');
INSERT INTO araba VALUES(3,'4');
INSERT INTO araba VALUES(5,'6');

INSERT INTO ismakinesi VALUES(4,'8.000');
INSERT INTO ismakinesi VALUES(6,'12.000');
INSERT INTO ismakinesi VALUES(7,'6.000');
INSERT INTO ismakinesi VALUES(8,'10.000');
 
 
INSERT INTO kiralananArac VALUES(1,1);
INSERT INTO kiralananArac VALUES(2,1);
INSERT INTO kiralananArac VALUES(3,3);
INSERT INTO kiralananArac VALUES(6,5);
INSERT INTO kiralananArac VALUES(7,8);

INSERT INTO islem VALUES(1,'100');
INSERT INTO islem VALUES(2,'1.000');
INSERT INTO islem VALUES(3,'300');
INSERT INTO islem VALUES(4,'500');
INSERT INTO islem VALUES(5,'600');
INSERT INTO islem VALUES(6);
INSERT INTO islem VALUES(7);
INSERT INTO islem VALUES(8);
INSERT INTO islem VALUES(9);
INSERT INTO islem VALUES(10,'155');
INSERT INTO islem VALUES(11,'1.230');
INSERT INTO islem VALUES(12,'600');
INSERT INTO islem VALUES(13,'5.000');
INSERT INTO islem VALUES(14,'5.000');
INSERT INTO islem VALUES(15,'6.000');

INSERT INTO teslimat VALUES(1,'12:00','21 Aralik 2016',1,1);
INSERT INTO teslimat VALUES(2,'16:30','21 Ocak 2017',2,1);
INSERT INTO teslimat VALUES(3,'16:30','12 Aralik 2016',3,3);
INSERT INTO teslimat VALUES(4,'19:00','12 Aralik 2016',6,5);
INSERT INTO teslimat VALUES(5,'13:00','16 Mart 2017',7,8);


INSERT INTO sozlesme VALUES(6,111,'Temsilci 1',1);
INSERT INTO sozlesme VALUES(7,222,'Temsilci 2',3);
INSERT INTO sozlesme VALUES(8,333,'Temsilci 1',5);
INSERT INTO sozlesme VALUES(9,444,'Temsilci 1',8);

INSERT INTO bakim VALUES(10,'X Sanayi', 'Lastik Degisim',1);
INSERT INTO bakim VALUES(11,'OSTIM Sanayi', 'Baski Balata Degisim',4);
INSERT INTO bakim VALUES(12,'Konya Yolu Sanayi', 'Genel Bakim',8);

    
INSERT INTO odeme VALUES(13,'Kredi Karti','5.000',1);
INSERT INTO odeme VALUES(14,'Nakit','5.000',2);
INSERT INTO odeme VALUES(15,'Kredi Karti','6.000',1);


ALTER TABLE ONLY kiralananArac
    ADD CONSTRAINT kira_arac FOREIGN KEY (kiralananAracID) REFERENCES arac(aracID) ON DELETE SET NULL;  

ALTER TABLE ONLY kiralananArac
    ADD CONSTRAINT kira_musteri FOREIGN KEY (kiralayanID) REFERENCES musteri(musteriID) ON DELETE SET NULL;      
    
ALTER TABLE ONLY kisi
    ADD CONSTRAINT hasmusteri_kisi FOREIGN KEY (kmusteriID) REFERENCES musteri(musteriID) ON DELETE SET NULL;
   
   
ALTER TABLE ONLY sirket
    ADD CONSTRAINT hasmusteri_sirket FOREIGN KEY (smusteriID) REFERENCES musteri(musteriID) ON DELETE SET NULL;   
     
ALTER TABLE ONLY arac
    ADD CONSTRAINT hasdurum FOREIGN KEY (durumID) REFERENCES durum(aracDurumID);   
   
ALTER TABLE ONLY araba
    ADD CONSTRAINT hasarac_araba FOREIGN KEY (arabaID) REFERENCES arac(aracID) ;
       
ALTER TABLE ONLY ismakinesi
    ADD CONSTRAINT hasarac_ismakinesi FOREIGN KEY (ismakinesiID) REFERENCES arac(aracID);  
       
ALTER TABLE ONLY teslimat
    ADD CONSTRAINT teslimat_islem FOREIGN KEY (TeslimatislemID) REFERENCES islem(islemID);   
 
ALTER TABLE ONLY teslimat
    ADD CONSTRAINT teslimat_musteri FOREIGN KEY (tMusteriID) REFERENCES musteri(musteriID);   
 
ALTER TABLE ONLY teslimat
    ADD CONSTRAINT teslimat_arac FOREIGN KEY (tAracID) REFERENCES arac(aracID);       

ALTER TABLE ONLY sozlesme
    ADD CONSTRAINT sozlesme_islem FOREIGN KEY (SozlesmeislemID) REFERENCES islem(islemID);   
 
ALTER TABLE ONLY sozlesme
    ADD CONSTRAINT sozlesme_musteri FOREIGN KEY (sMusteriID) REFERENCES musteri(musteriID);     
    
ALTER TABLE ONLY bakim
    ADD CONSTRAINT bakim_islem FOREIGN KEY (BakimislemID) REFERENCES islem(islemID);   
 
ALTER TABLE ONLY bakim
    ADD CONSTRAINT bakim_arac FOREIGN KEY (bAracID) REFERENCES arac(aracID);       
 
ALTER TABLE ONLY odeme
    ADD CONSTRAINT odeme_islem FOREIGN KEY (OdemeislemID) REFERENCES islem(islemID);   
 
ALTER TABLE ONLY odeme
    ADD CONSTRAINT odeme_musteri FOREIGN KEY (oMusteriID) REFERENCES musteri(musteriID);       
--
-- TOC entry 2083 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2016-11-10 23:51:42 EET

--
-- PostgreSQL database dump complete
--
