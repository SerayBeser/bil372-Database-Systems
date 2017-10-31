import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

public class arabaKirala extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

	private Connection connection = null;
	private String url = "jdbc:postgresql://127.0.0.1:5432/aracKirala";
	private String userName = "postgres";
	private String driver = "org.postgresql.Driver";
	private String password = "123456";

	private ResultSet resultMusteri, resultMusteri2, resultKisi, resultSirket, resultArac, resultArac2, resultDurum,
			resultAraba, resultIsMakinesi, resultKira, resultIslem, resultTeslimat, resultSozlesme, resultBakim,
			resultOdeme;

	private Statement statement, statement2, statement3, statement4, statement5, statement6, statement7, statement8,
			statement9, statement10, statement11, statement12, statement13;

	private JMenuBar menuBar;
	private JMenuItem anaSayfa, menuHakkinda, menuYeniKayit, menuListele, menuYeniKayitKisi, menuListeleKisi,
			menuSilMusteri, menuYeniKayitSirket, menuListeleSirket, menuYeniKayitArac, menuListeleArac, menuSilAraba,
			menuSilIsMakinesi, menuYeniKayitAraba, menuListeleAraba, menuYeniKayitIsMakinesi, menuListeleIsMakinesi,
			menuGuncelleMusteri, menuGuncelleKisi, menuGuncelleSirket, menuGuncelleArac, menuGuncelleAraba,
			menuGuncelleIsMakinesi, menuKirala, menuAracDurum, menuListeleDurum, menuTeslimAlAraci, menuSilDurum,
			menuGuncelleDurum, menuAramaMusteri, menuAramaKisi, menuAramaSirket, menuAramaArac, menuAramaAraba,
			menuAramaIsMakinesi, menuAramaDurum, menuAramaKira, menuYeniIslem, menuListeleIslem, menuGuncelleIslem,
			menuAramaIslem, menuYeniTeslimat, menuListeleTeslimat, menuGuncelleTeslimat, menuSilTeslimat,
			menuAramaTeslimat, menuYeniSozlesme, menuListeleSozlesme, menuGuncelleSozlesme, menuAramaSozlesme,
			menuSilSozlesme, menuYeniBakim, menuListeleBakim, menuGuncelleBakim, menuSilBakim, menuAramaBakim,
			menuYeniOdeme, menuListeleOdeme, menuGuncelleOdeme, menuAramaOdeme;

	private JPanel anaPanel, yeniPanel;
	private Container container;

	private JLabel musteriNumarasi, musteriTelefon, musteriAdres, musteriMail, kisiID, kisiIsim, kisiSoyisim,
			kisiCinsiyet, kisiYas, kisiTC, kisiEhliyet, sirketID, sirketIsim, aracID, durumID, plakaNo, vites, marka,
			model, fiyat, kasko, km, yakit, renk, motor, durumBilgisi, kapiSayisi, arabaID, tonaj, isID,
			KiralananaracID, AracKiralayanMusteriID, aracDurumID, durum, islemNumarasi, islemTutari, teslimatNumarasi,
			teslimatSaat, teslimatTarih, teslimArac, teslimMusteri, sozlesmeNumarasi, sozlesmeOzel, temsilci,
			sozlesmeMusteri, bakimNumarasi, bakimYeri, bakimdaYapilanIslem, bakimArac, odemeNumarasi, odemeSekil,
			odemeTutar, odemeMusteri;

	private JTextField txtmusteriNumarasi, txtmusteriTelefon, txtmusteriAdres, txtmusteriMail, txtmusteriAracSayisi,
			txtkisiID, txtkisiIsim, txtKisiSoyisim, txtkisiCinsiyet, txtkisiYas, txtkisiTC, txtkisiEhliyet, txtsirketID,
			txtsirketIsim, txtGuncelle, txtaracID, txtdurumID, txtplakaNo, txtvites, txtmarka, txtmodel, txtfiyat,
			txtkasko, txtkm, txtyakit, txtrenk, txtmotor, txtdurumBilgisi, txtkapiSayisi, txtisID, txttonaj,
			txtKiralananaracID, txtaracDurumID, txtdurum, txtarabaID, txtAracKiralayanMusteriID, txtislemNumarasi,
			txtislemTutari, txtteslimMusteri, txtteslimArac, txtteslimatTarih, txtteslimatNumarasi, txtteslimatSaat,
			txtsozlesmeNumarasi, txtsozlesmeOzel, txttemsilci, txtsozlesmeMusteri, txtbakimNumarasi, txtbakimYeri,
			txtbakimdaYapilanIslem, txtbakimArac, txtodemeNumarasi, txtodemeSekil, txtodemeTutar, txtodemeMusteri;

	private JTable tablo, tabloKisi, tabloSirket, tabloArac, tabloAraba, tabloIsMakinesi, tabloKira, tabloDurum,
			tabloIslem, tabloTeslimat, tabloSozlesme, tabloBakim, tabloOdeme;

	private JButton btnKaydet, guncelleSec;

	public arabaKirala() {

		super("ARAC KIRALAMA SISTEMI");

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1100, 600);
		this.setResizable(false);
		acilisEkrani();

		try {
			statement = baglanti();
			resultMusteri = statement.executeQuery("SELECT * FROM musteri");
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, "Baglantı Basarısız Musteri baglanti", "Postgresql Baglantısı",
					JOptionPane.PLAIN_MESSAGE);
		}

		try {
			statement2 = baglanti();
			resultKisi = statement2.executeQuery("SELECT * FROM kisi");
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, "Baglantı Basarısız Kisi baglanti", "Postgresql Baglantısı",
					JOptionPane.PLAIN_MESSAGE);

		}

		try {
			statement3 = baglanti();
			resultSirket = statement3.executeQuery("SELECT * FROM sirket");
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, "Baglantı Basarısız Sirket baglanti", "Postgresql Baglantısı",
					JOptionPane.PLAIN_MESSAGE);

		}
		try {
			statement4 = baglanti();
			resultArac = statement4.executeQuery("SELECT * FROM arac");

		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, "Baglantı Basarısız Arac baglanti", "Postgresql Baglantısı",
					JOptionPane.PLAIN_MESSAGE);

		}
		try {
			statement5 = baglanti();
			resultDurum = statement5.executeQuery("SELECT * FROM durum");

		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, "Baglantı Basarısız Durum baglanti", "Postgresql Baglantısı",
					JOptionPane.PLAIN_MESSAGE);

		}

		try {
			statement6 = baglanti();
			resultAraba = statement6.executeQuery("SELECT * FROM araba");

		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, "Baglantı Basarısız Araba baglanti", "Postgresql Baglantısı",
					JOptionPane.PLAIN_MESSAGE);

		}

		try {
			statement7 = baglanti();
			resultIsMakinesi = statement7.executeQuery("SELECT * FROM ismakinesi");

		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, "Baglantı Basarısız Is makinesi baglanti", "Postgresql Baglantısı",
					JOptionPane.PLAIN_MESSAGE);

		}

		try {
			statement8 = baglanti();
			resultKira = statement8.executeQuery("SELECT * FROM kiralananArac");

		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, "Baglantı Basarısız KiralananArac baglanti", "Postgresql Baglantısı",
					JOptionPane.PLAIN_MESSAGE);

		}
		try {
			statement9 = baglanti();
			resultIslem = statement9.executeQuery("SELECT * FROM islem");

		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, "Baglantı Basarısız Islem baglanti", "Postgresql Baglantısı",
					JOptionPane.PLAIN_MESSAGE);

		}
		try {
			statement10 = baglanti();
			resultTeslimat = statement10.executeQuery("SELECT * FROM teslimat");

		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, "Baglantı Basarısız Teslimat baglanti", "Postgresql Baglantısı",
					JOptionPane.PLAIN_MESSAGE);

		}
		try {
			statement11 = baglanti();
			resultSozlesme = statement11.executeQuery("SELECT * FROM sozlesme");

		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, "Baglantı Basarısız Sozlesme baglanti", "Postgresql Baglantısı",
					JOptionPane.PLAIN_MESSAGE);

		}

		try {
			statement12 = baglanti();
			resultBakim = statement12.executeQuery("SELECT * FROM bakim");

		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, "Baglantı Basarısız Bakim baglanti", "Postgresql Baglantısı",
					JOptionPane.PLAIN_MESSAGE);

		}
		try {
			statement13 = baglanti();
			resultOdeme = statement13.executeQuery("SELECT * FROM odeme");

		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, "Baglantı Basarısız Odeme baglanti", "Postgresql Baglantısı",
					JOptionPane.PLAIN_MESSAGE);

		}
		Toolkit toolkit = getToolkit();
		Dimension size = toolkit.getScreenSize();
		this.setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);

		menuBar = new JMenuBar();

		this.setJMenuBar(menuBar);

		JMenu AnaSayfa = new JMenu("ANASAYFA");
		menuBar.add(AnaSayfa);

		JMenu AracMenu = new JMenu("ARAC");
		menuBar.add(AracMenu);

		JMenu AracDurum = new JMenu("ARAC DURUM");
		menuBar.add(AracDurum);

		JMenu ArabaMenu = new JMenu("ARABA");
		menuBar.add(ArabaMenu);

		JMenu IsMenu = new JMenu("IS MAKINESI");
		menuBar.add(IsMenu);

		JMenu MusteriMenu = new JMenu("MUSTERI");
		menuBar.add(MusteriMenu);

		JMenu KisiMenu = new JMenu("KISI");
		menuBar.add(KisiMenu);

		JMenu SirketMenu = new JMenu("SIRKET");
		menuBar.add(SirketMenu);

		JMenu KiraMenu = new JMenu("ARAC KİRALA");
		menuBar.add(KiraMenu);

		JMenu IslemMenu = new JMenu("ISLEM");
		menuBar.add(IslemMenu);

		JMenu TeslimatMenu = new JMenu("TESLIMAT");
		menuBar.add(TeslimatMenu);

		JMenu SozlesmeMenu = new JMenu("SOZLESME");
		menuBar.add(SozlesmeMenu);

		JMenu BakimMenu = new JMenu("BAKIM");
		menuBar.add(BakimMenu);

		JMenu OdemeMenu = new JMenu("ODEME");
		menuBar.add(OdemeMenu);

		anaSayfa = new JMenuItem("AnaSayfa");
		AnaSayfa.add(anaSayfa);

		anaSayfa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				acilisEkrani();
			}

		});

		menuHakkinda = new JMenuItem("Hakkında");
		AnaSayfa.add(menuHakkinda);

		menuHakkinda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hakkindaMenusu();
			}

		});

		menuYeniKayit = new JMenuItem("Yeni Musteri");
		MusteriMenu.add(menuYeniKayit);

		menuYeniKayit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				yeniKayitMenusu();
			}

		});

		menuListele = new JMenuItem("Musteri Listele");
		MusteriMenu.add(menuListele);

		menuListele.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					listelemeYap("SELECT * FROM musteri");
				} catch (SQLException e1) {

					JOptionPane.showConfirmDialog(null, "Baglantı Basarısız Musteri Listeleme", "Postgresql Baglantısı",
							JOptionPane.PLAIN_MESSAGE);
					e1.printStackTrace();
				}

			}
		});
		menuGuncelleMusteri = new JMenuItem("Musteri Güncelle");
		MusteriMenu.add(menuGuncelleMusteri);

		menuGuncelleMusteri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guncellemeMenusuMusteri();
			}

		});
		menuAramaMusteri = new JMenuItem("Musteri Arama");
		MusteriMenu.add(menuAramaMusteri);

		menuAramaMusteri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aramaMenusuMusteri();
			}
		});
		menuYeniKayitKisi = new JMenuItem("Yeni Kisi");
		KisiMenu.add(menuYeniKayitKisi);

		menuYeniKayitKisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				yeniKayitKisiMenusu();
			}

		});

		menuListeleKisi = new JMenuItem("Kisi Listele");
		KisiMenu.add(menuListeleKisi);

		menuListeleKisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					listelemeYapKisi("SELECT * FROM kisi");
				} catch (SQLException e1) {

					JOptionPane.showConfirmDialog(null, "Baglantı Basarısız Kisi listele", "Postgresql Baglantısı",
							JOptionPane.PLAIN_MESSAGE);
					e1.printStackTrace();
				}

			}
		});
		menuGuncelleKisi = new JMenuItem("Kisi Güncelle");
		KisiMenu.add(menuGuncelleKisi);

		menuGuncelleKisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guncellemeMenusuKisi();
			}

		});
		menuSilMusteri = new JMenuItem("Kisi Sil");
		KisiMenu.add(menuSilMusteri);

		menuSilMusteri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kisiSil();
			}

		});
		menuAramaKisi = new JMenuItem("Kisi Arama");
		KisiMenu.add(menuAramaKisi);

		menuAramaKisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aramaMenusuKisi();
			}
		});
		menuYeniKayitSirket = new JMenuItem("Yeni Sirket");
		SirketMenu.add(menuYeniKayitSirket);

		menuYeniKayitSirket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				yeniKayitSirketMenusu();
			}

		});

		menuListeleSirket = new JMenuItem("Sirket Listele");
		SirketMenu.add(menuListeleSirket);

		menuListeleSirket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					listelemeYapSirket("SELECT * FROM sirket");
				} catch (SQLException e1) {

					JOptionPane.showConfirmDialog(null, "Baglantı Basarısız Sirket listele", "Postgresql Baglantısı",
							JOptionPane.PLAIN_MESSAGE);
					e1.printStackTrace();
				}

			}
		});
		menuGuncelleSirket = new JMenuItem("Sirket Güncelle");
		SirketMenu.add(menuGuncelleSirket);

		menuGuncelleSirket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guncellemeMenusuSirket();
			}

		});

		menuSilMusteri = new JMenuItem("Sirket Sil");
		SirketMenu.add(menuSilMusteri);

		menuSilMusteri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sirketSil();
			}

		});

		menuAramaSirket = new JMenuItem("Sirket Arama");
		SirketMenu.add(menuAramaSirket);

		menuAramaSirket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aramaMenusuSirket();
			}
		});
		menuYeniKayitArac = new JMenuItem("Yeni Arac");
		AracMenu.add(menuYeniKayitArac);

		menuYeniKayitArac.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				yeniKayitAracMenusu();
			}

		});

		menuListeleArac = new JMenuItem("Arac Listele");
		AracMenu.add(menuListeleArac);

		menuListeleArac.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					listelemeYapArac("SELECT * FROM arac");
				} catch (SQLException e1) {

					JOptionPane.showConfirmDialog(null, "Baglantı Basarısız  Listeleme Arac", "Postgresql Baglantısı",
							JOptionPane.PLAIN_MESSAGE);
					e1.printStackTrace();
				}

			}
		});
		menuGuncelleArac = new JMenuItem("Arac Güncelle");
		AracMenu.add(menuGuncelleArac);

		menuGuncelleArac.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guncellemeMenusuArac();
			}

		});
		menuAramaArac = new JMenuItem("Arac Arama");
		AracMenu.add(menuAramaArac);

		menuAramaArac.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aramaMenusuArac();
			}
		});

		menuAracDurum = new JMenuItem("Arac Durum Bilgisi Ekle");
		AracDurum.add(menuAracDurum);

		menuAracDurum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aracDurumMenusu();
			}

		});

		menuListeleDurum = new JMenuItem("Arac Durum Listele");
		AracDurum.add(menuListeleDurum);

		menuListeleDurum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					listelemeYapAracDurum("SELECT * FROM durum");
				} catch (SQLException e1) {

					JOptionPane.showConfirmDialog(null, "Baglantı Basarısız  Listeleme Arac Durumu",
							"Postgresql Baglantısı", JOptionPane.PLAIN_MESSAGE);
					e1.printStackTrace();
				}

			}
		});

		menuGuncelleDurum = new JMenuItem("Arac Durum Bilgisi Guncelle");
		AracDurum.add(menuGuncelleDurum);

		menuGuncelleDurum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guncellemeMenusuDurum();
			}

		});
		menuSilDurum = new JMenuItem("Arac Durum Bilgisi Sil");
		AracDurum.add(menuSilDurum);

		menuSilDurum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				durumSil();
			}

		});
		menuAramaDurum = new JMenuItem("Arac Durum Bilgisi Arama");
		AracDurum.add(menuAramaDurum);

		menuAramaDurum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aramaMenusuDurum();
			}
		});

		menuYeniKayitAraba = new JMenuItem("Yeni Araba");
		ArabaMenu.add(menuYeniKayitAraba);

		menuYeniKayitAraba.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				yeniKayitArabaMenusu();
			}

		});

		menuListeleAraba = new JMenuItem("Araba Listele");
		ArabaMenu.add(menuListeleAraba);

		menuListeleAraba.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					listelemeYapAraba("SELECT * FROM araba");
				} catch (SQLException e1) {

					JOptionPane.showConfirmDialog(null, "Baglantı Basarısız  Listeleme Araba", "Postgresql Baglantısı",
							JOptionPane.PLAIN_MESSAGE);
					e1.printStackTrace();
				}

			}
		});
		menuGuncelleAraba = new JMenuItem("Araba Güncelle");
		ArabaMenu.add(menuGuncelleAraba);

		menuGuncelleAraba.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guncellemeMenusuAraba();
			}

		});
		menuSilAraba = new JMenuItem("Araba Sil");
		ArabaMenu.add(menuSilAraba);

		menuSilAraba.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				arabaSil();
			}

		});
		menuAramaAraba = new JMenuItem("Araba Arama");
		ArabaMenu.add(menuAramaAraba);

		menuAramaAraba.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aramaMenusuAraba();
			}
		});
		menuYeniKayitIsMakinesi = new JMenuItem("Yeni IsMakinesi");
		IsMenu.add(menuYeniKayitIsMakinesi);

		menuYeniKayitIsMakinesi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				yeniKayitIsMakinesiMenusu();
			}

		});
		menuListeleIsMakinesi = new JMenuItem("IsMakinesi Listele");
		IsMenu.add(menuListeleIsMakinesi);

		menuListeleIsMakinesi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					listelemeYapIsMakinesi("SELECT * FROM ismakinesi");
				} catch (SQLException e1) {

					JOptionPane.showConfirmDialog(null, "Baglantı Basarısız  Listeleme Is makinesi",
							"Postgresql Baglantısı", JOptionPane.PLAIN_MESSAGE);
					e1.printStackTrace();
				}

			}
		});

		menuGuncelleIsMakinesi = new JMenuItem("Is Makinesi Güncelle");
		IsMenu.add(menuGuncelleIsMakinesi);

		menuGuncelleIsMakinesi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guncellemeMenusuIsMakinesi();
			}

		});
		menuSilIsMakinesi = new JMenuItem("IsMakinesi Sil");
		IsMenu.add(menuSilIsMakinesi);

		menuSilIsMakinesi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isMakinesiSil();
			}

		});
		menuAramaIsMakinesi = new JMenuItem("Is Makinesi Arama");
		IsMenu.add(menuAramaIsMakinesi);

		menuAramaIsMakinesi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aramaMenusuIsMakinesi();
			}
		});

		menuKirala = new JMenuItem("Arac Kirala");
		KiraMenu.add(menuKirala);

		menuKirala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kirala();
			}

		});
		menuListele = new JMenuItem("Kiralanan Arac Listele");
		KiraMenu.add(menuListele);

		menuListele.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					listelemeYapKiralananArac("SELECT * FROM kiralananArac");
				} catch (SQLException e1) {

					JOptionPane.showConfirmDialog(null, "Baglantı Basarısız Arac Kirala Listeleme",
							"Postgresql Baglantısı", JOptionPane.PLAIN_MESSAGE);
					e1.printStackTrace();
				}

			}
		});

		menuTeslimAlAraci = new JMenuItem("Arac Teslim Al");
		KiraMenu.add(menuTeslimAlAraci);

		menuTeslimAlAraci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teslimAl();
			}

		});

		menuAramaKira = new JMenuItem("Arac Kiralama Bilgisi Arama");
		KiraMenu.add(menuAramaKira);

		menuAramaKira.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aramaMenusuKira();
			}
		});

		menuYeniIslem = new JMenuItem("Yeni Islem");
		IslemMenu.add(menuYeniIslem);

		menuYeniIslem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				yeniKayitMenusuIslem();
			}

		});

		menuListeleIslem = new JMenuItem("Islem Listele");
		IslemMenu.add(menuListeleIslem);

		menuListeleIslem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					listelemeYapIslem("SELECT * FROM islem");
				} catch (SQLException e1) {

					JOptionPane.showConfirmDialog(null, "Baglantı Basarısız Islem Listeleme", "Postgresql Baglantısı",
							JOptionPane.PLAIN_MESSAGE);
					e1.printStackTrace();
				}

			}
		});
		menuGuncelleIslem = new JMenuItem("Islem Güncelle");
		IslemMenu.add(menuGuncelleIslem);

		menuGuncelleIslem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guncellemeMenusuIslem();
			}

		});
		menuAramaIslem = new JMenuItem("Islem Arama");
		IslemMenu.add(menuAramaIslem);

		menuAramaIslem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aramaMenusuIslem();
			}
		});

		menuYeniTeslimat = new JMenuItem("Yeni Teslimat");
		TeslimatMenu.add(menuYeniTeslimat);

		menuYeniTeslimat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				yeniKayitMenusuTeslimat();
			}

		});

		menuListeleTeslimat = new JMenuItem("Teslimat Listele");
		TeslimatMenu.add(menuListeleTeslimat);

		menuListeleTeslimat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					listelemeYapTeslimat("SELECT * FROM teslimat");
				} catch (SQLException e1) {

					JOptionPane.showConfirmDialog(null, "Baglantı Basarısız Teslimat Listeleme",
							"Postgresql Baglantısı", JOptionPane.PLAIN_MESSAGE);
					e1.printStackTrace();
				}

			}
		});

		menuGuncelleTeslimat = new JMenuItem("Teslimat Güncelle");
		TeslimatMenu.add(menuGuncelleTeslimat);

		menuGuncelleTeslimat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guncellemeMenusuTeslimat();
			}

		});

		menuSilTeslimat = new JMenuItem("Teslimat Sil");
		TeslimatMenu.add(menuSilTeslimat);

		menuSilTeslimat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teslimatSil();
			}

		});

		menuAramaTeslimat = new JMenuItem("Teslimat Arama");
		TeslimatMenu.add(menuAramaTeslimat);

		menuAramaTeslimat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aramaMenusuTeslimat();
			}
		});

		menuYeniSozlesme = new JMenuItem("Yeni Sozlesme");
		SozlesmeMenu.add(menuYeniSozlesme);

		menuYeniSozlesme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				yeniKayitMenusuSozlesme();
			}

		});

		menuListeleSozlesme = new JMenuItem("Sozlesme Listele");
		SozlesmeMenu.add(menuListeleSozlesme);

		menuListeleSozlesme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					listelemeYapSozlesme("SELECT * FROM sozlesme");
				} catch (SQLException e1) {

					JOptionPane.showConfirmDialog(null, "Baglantı Basarısız Sozlesme Listeleme",
							"Postgresql Baglantısı", JOptionPane.PLAIN_MESSAGE);
					e1.printStackTrace();
				}

			}
		});

		menuGuncelleSozlesme = new JMenuItem("Sozlesme Güncelle");
		SozlesmeMenu.add(menuGuncelleSozlesme);

		menuGuncelleSozlesme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guncellemeMenusuSozlesme();
			}

		});

		menuSilSozlesme = new JMenuItem("Sozlesme Sil");
		SozlesmeMenu.add(menuSilSozlesme);

		menuSilSozlesme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sozlesmeSil();
			}

		});

		menuAramaSozlesme = new JMenuItem("Sozlesme Arama");
		SozlesmeMenu.add(menuAramaSozlesme);

		menuAramaSozlesme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aramaMenusuSozlesme();
			}
		});

		menuYeniBakim = new JMenuItem("Yeni Bakim");
		BakimMenu.add(menuYeniBakim);

		menuYeniBakim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				yeniKayitMenusuBakim();
			}

		});
		menuListeleBakim = new JMenuItem("Bakim Listele");
		BakimMenu.add(menuListeleBakim);

		menuListeleBakim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					listelemeYapBakim("SELECT * FROM bakim");
				} catch (SQLException e1) {

					JOptionPane.showConfirmDialog(null, "Baglantı Basarısız Bakim Listeleme", "Postgresql Baglantısı",
							JOptionPane.PLAIN_MESSAGE);
					e1.printStackTrace();
				}

			}
		});

		menuGuncelleBakim = new JMenuItem("Bakim Güncelle");
		BakimMenu.add(menuGuncelleBakim);

		menuGuncelleBakim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guncellemeMenusuBakim();
			}

		});

		menuSilBakim = new JMenuItem("Bakim Sil");
		BakimMenu.add(menuSilBakim);

		menuSilBakim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bakimSil();
			}

		});

		menuAramaBakim = new JMenuItem("Bakim Arama");
		BakimMenu.add(menuAramaBakim);

		menuAramaBakim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aramaMenusuBakim();
			}
		});

		menuYeniOdeme = new JMenuItem("Yeni Odeme");
		OdemeMenu.add(menuYeniOdeme);

		menuYeniOdeme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				yeniKayitMenusuOdeme();
			}

		});
		menuListeleOdeme = new JMenuItem("Odeme Listele");
		OdemeMenu.add(menuListeleOdeme);

		menuListeleOdeme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					listelemeYapOdeme("SELECT * FROM odeme");
				} catch (SQLException e1) {

					JOptionPane.showConfirmDialog(null, "Baglantı Basarısız Odeme Listeleme", "Postgresql Baglantısı",
							JOptionPane.PLAIN_MESSAGE);
					e1.printStackTrace();
				}

			}
		});

		menuGuncelleOdeme = new JMenuItem("Odeme Güncelle");
		OdemeMenu.add(menuGuncelleOdeme);

		menuGuncelleOdeme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guncellemeMenusuOdeme();
			}

		});

		menuSilBakim = new JMenuItem("Odeme Sil");
		OdemeMenu.add(menuSilBakim);

		menuSilBakim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				odemeSil();
			}

		});

		menuAramaOdeme = new JMenuItem("Odeme Arama");
		OdemeMenu.add(menuAramaOdeme);

		menuAramaOdeme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aramaMenusuOdeme();
			}
		});

		anaPanel = new JPanel(null);
		anaPanel.setSize(1100, 600);
		anaPanel.setBounds(0, 500, 500, 450);
		this.getContentPane().add(anaPanel);

	}

	public void musteriNumarasiOlustur() {

		musteriNumarasi = new JLabel("Musteri ID");
		musteriNumarasi.setSize(100, 30);
		musteriNumarasi.setBounds(10, 50, 150, 30);
		yeniPanel.add(musteriNumarasi);
		txtmusteriNumarasi = new JTextField();
		txtmusteriNumarasi.setSize(250, 20);
		txtmusteriNumarasi.setBounds(125, 58, 250, 20);
		// txtmusteriNumarasi.setEditable(false);
		yeniPanel.add(txtmusteriNumarasi);
	}

	public void musteriTelefonOlustur() {

		musteriTelefon = new JLabel("Musteri Telefon");
		musteriTelefon.setSize(100, 30);
		musteriTelefon.setBounds(10, 80, 150, 30);
		yeniPanel.add(musteriTelefon);
		txtmusteriTelefon = new JTextField();
		txtmusteriTelefon.setSize(250, 20);
		txtmusteriTelefon.setBounds(125, 88, 250, 20);
		yeniPanel.add(txtmusteriTelefon);

	}

	public void musteriAdresOlustur() {

		musteriAdres = new JLabel("Musteri Adresi");
		musteriAdres.setSize(100, 30);
		musteriAdres.setBounds(10, 110, 150, 30);
		yeniPanel.add(musteriAdres);
		txtmusteriAdres = new JTextField();
		txtmusteriAdres.setSize(250, 20);
		txtmusteriAdres.setBounds(125, 118, 250, 20);
		yeniPanel.add(txtmusteriAdres);

	}

	public void musteriMailOlustur() {

		musteriMail = new JLabel("Musteri Mail:");
		musteriMail.setSize(100, 30);
		musteriMail.setBounds(10, 140, 150, 30);
		yeniPanel.add(musteriMail);
		txtmusteriMail = new JTextField();
		txtmusteriMail.setSize(250, 20);
		txtmusteriMail.setBounds(125, 148, 250, 20);
		yeniPanel.add(txtmusteriMail);

	}

	public void kisiNumarasiOlustur() {

		kisiID = new JLabel("Kisi ID: ");
		kisiID.setSize(100, 30);
		kisiID.setBounds(10, 50, 150, 30);
		yeniPanel.add(kisiID);
		txtkisiID = new JTextField();
		txtkisiID.setSize(250, 20);
		txtkisiID.setBounds(125, 58, 250, 20);
		yeniPanel.add(txtkisiID);

	}

	public void kisiIsimOlustur() {

		kisiIsim = new JLabel("Kisi İsmi: ");
		kisiIsim.setSize(100, 30);
		kisiIsim.setBounds(10, 80, 150, 30);
		yeniPanel.add(kisiIsim);
		txtkisiIsim = new JTextField();
		txtkisiIsim.setSize(250, 20);
		txtkisiIsim.setBounds(125, 88, 250, 20);
		yeniPanel.add(txtkisiIsim);

	}

	public void kisiSoyisimOlustur() {

		kisiSoyisim = new JLabel("Kisi Soyismi: ");
		kisiSoyisim.setSize(100, 30);
		kisiSoyisim.setBounds(10, 110, 150, 30);
		yeniPanel.add(kisiSoyisim);
		txtKisiSoyisim = new JTextField();
		txtKisiSoyisim.setSize(250, 20);
		txtKisiSoyisim.setBounds(125, 118, 250, 20);
		yeniPanel.add(txtKisiSoyisim);

	}

	public void kisiCinsiyetOlustur() {

		kisiCinsiyet = new JLabel("Kisi Cinsiyet: ");
		kisiCinsiyet.setSize(100, 30);
		kisiCinsiyet.setBounds(10, 140, 150, 30);
		yeniPanel.add(kisiCinsiyet);
		txtkisiCinsiyet = new JTextField();
		txtkisiCinsiyet.setSize(250, 20);
		txtkisiCinsiyet.setBounds(125, 148, 250, 20);
		yeniPanel.add(txtkisiCinsiyet);

	}

	public void kisiYasOlustur() {

		kisiYas = new JLabel("Kisi Yas: ");
		kisiYas.setSize(100, 30);
		kisiYas.setBounds(10, 170, 150, 30);
		yeniPanel.add(kisiYas);
		txtkisiYas = new JTextField();
		txtkisiYas.setSize(250, 20);
		txtkisiYas.setBounds(125, 178, 250, 20);
		yeniPanel.add(txtkisiYas);

	}

	public void kisiTCOlustur() {

		kisiTC = new JLabel("Kisi TC: ");
		kisiTC.setSize(100, 30);
		kisiTC.setBounds(10, 200, 150, 30);
		yeniPanel.add(kisiTC);
		txtkisiTC = new JTextField();
		txtkisiTC.setSize(250, 20);
		txtkisiTC.setBounds(125, 208, 250, 20);
		yeniPanel.add(txtkisiTC);

	}

	public void kisiEhliyetOlustur() {

		kisiEhliyet = new JLabel("Kisi Ehliyet: ");
		kisiEhliyet.setSize(100, 30);
		kisiEhliyet.setBounds(10, 230, 150, 30);
		yeniPanel.add(kisiEhliyet);
		txtkisiEhliyet = new JTextField();
		txtkisiEhliyet.setSize(250, 20);
		txtkisiEhliyet.setBounds(125, 238, 250, 20);
		yeniPanel.add(txtkisiEhliyet);

	}

	public void sirketNumarasiOlustur() {

		sirketID = new JLabel("Sirket ID: ");
		sirketID.setSize(100, 30);
		sirketID.setBounds(10, 50, 150, 30);
		yeniPanel.add(sirketID);
		txtsirketID = new JTextField();
		txtsirketID.setSize(250, 20);
		txtsirketID.setBounds(125, 58, 250, 20);
		yeniPanel.add(txtsirketID);

	}

	public void sirketIsmiOlustur() {

		sirketIsim = new JLabel("Sirket Ismi: ");
		sirketIsim.setSize(100, 30);
		sirketIsim.setBounds(10, 80, 150, 30);
		yeniPanel.add(sirketIsim);
		txtsirketIsim = new JTextField();
		txtsirketIsim.setSize(250, 20);
		txtsirketIsim.setBounds(125, 88, 250, 20);
		yeniPanel.add(txtsirketIsim);

	}

	public void yeniKayitMenusu() {

		container = getContentPane();
		container.removeAll();
		yeniPanel = new JPanel(null);
		yeniPanel.setSize(1100, 600);
		yeniPanel.setBounds(0, 0, 1100, 600);

		yeniPanel.setBackground(Color.white);

		musteriNumarasiOlustur();
		musteriTelefonOlustur();
		musteriAdresOlustur();
		musteriMailOlustur();

		JTextArea txtAnaSayfa = new JTextArea();
		txtAnaSayfa.setText("             MÜsTERİ BİLGİLERİ");
		Font font = new Font("Default", Font.CENTER_BASELINE, 16);
		txtAnaSayfa.setFont(font);

		// Color color=new
		// Color(115,44,123);//http://www.colorcombos.com/colors/732C7B
		// purple

		txtAnaSayfa.setForeground(Color.BLACK);
		txtAnaSayfa.setSize(460, 40);
		txtAnaSayfa.setBounds(15, 15, 460, 30);

		Color color2 = UIManager.getColor("Panel.background");

		txtAnaSayfa.setBackground(color2);
		txtAnaSayfa.setEditable(false);

		yeniPanel.add(txtAnaSayfa);
		btnKaydet = new JButton(" Kaydet");
		btnKaydet.setSize(100, 30);
		btnKaydet.setBounds(271, 530, 200, 30);

		btnKaydet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				musteriYeniKayit();
			}

		});

		yeniPanel.add(btnKaydet);
		container.add(yeniPanel);
		invalidate();
		repaint();
	}

	public void yeniKayitKisiMenusu() {

		container = getContentPane();
		container.removeAll();
		yeniPanel = new JPanel(null);
		yeniPanel.setSize(1100, 600);
		yeniPanel.setBounds(0, 0, 1100, 600);
		yeniPanel.setBackground(Color.white);

		kisiNumarasiOlustur();
		kisiIsimOlustur();
		kisiSoyisimOlustur();
		kisiCinsiyetOlustur();
		kisiYasOlustur();
		kisiTCOlustur();
		kisiEhliyetOlustur();

		JTextArea txtAnaSayfa = new JTextArea();
		txtAnaSayfa.setText("             KİSİ BİLGİLERİ");
		Font font = new Font("Default", Font.CENTER_BASELINE, 16);
		txtAnaSayfa.setFont(font);

		// Color color=new
		// Color(115,44,123);//http://www.colorcombos.com/colors/732C7B
		// purple

		txtAnaSayfa.setForeground(Color.BLACK);
		txtAnaSayfa.setSize(460, 40);
		txtAnaSayfa.setBounds(15, 15, 460, 30);

		Color color2 = UIManager.getColor("Panel.background");

		txtAnaSayfa.setBackground(color2);
		txtAnaSayfa.setEditable(false);

		yeniPanel.add(txtAnaSayfa);
		btnKaydet = new JButton("Kaydet");
		btnKaydet.setSize(100, 30);
		btnKaydet.setBounds(271, 530, 200, 30);

		btnKaydet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kisiYeniKayit();
			}

		});

		yeniPanel.add(btnKaydet);
		container.add(yeniPanel);
		invalidate();
		repaint();
	}

	public void yeniKayitSirketMenusu() {

		container = getContentPane();
		container.removeAll();
		yeniPanel = new JPanel(null);
		yeniPanel.setSize(1100, 600);
		yeniPanel.setBounds(0, 0, 1100, 600);
		yeniPanel.setBackground(Color.white);

		sirketNumarasiOlustur();
		sirketIsmiOlustur();

		JTextArea txtAnaSayfa = new JTextArea();
		txtAnaSayfa.setText("             SİRKET BİLGİLERİ");
		Font font = new Font("Default", Font.CENTER_BASELINE, 16);
		txtAnaSayfa.setFont(font);

		// Color color=new
		// Color(115,44,123);//http://www.colorcombos.com/colors/732C7B
		// purple

		txtAnaSayfa.setForeground(Color.BLACK);
		txtAnaSayfa.setSize(460, 40);
		txtAnaSayfa.setBounds(15, 15, 460, 30);

		Color color2 = UIManager.getColor("Panel.background");

		txtAnaSayfa.setBackground(color2);
		txtAnaSayfa.setEditable(false);

		yeniPanel.add(txtAnaSayfa);
		btnKaydet = new JButton("Kaydet");
		btnKaydet.setSize(100, 30);
		btnKaydet.setBounds(271, 530, 200, 30);

		btnKaydet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sirketYeniKayit();
			}

		});

		yeniPanel.add(btnKaydet);
		container.add(yeniPanel);
		invalidate();
		repaint();
	}

	public void aracIDOlustur() {

		aracID = new JLabel("Arac ID: ");
		aracID.setSize(100, 30);
		aracID.setBounds(10, 50, 150, 30);
		yeniPanel.add(aracID);
		txtaracID = new JTextField();
		txtaracID.setSize(250, 20);
		txtaracID.setBounds(125, 58, 250, 20);
		yeniPanel.add(txtaracID);

	}

	public void durumIDOlustur() {

		durumID = new JLabel("Durum ID: ");
		durumID.setSize(100, 30);
		durumID.setBounds(10, 80, 150, 30);
		yeniPanel.add(durumID);
		txtdurumID = new JTextField();
		txtdurumID.setSize(250, 20);
		txtdurumID.setBounds(125, 88, 250, 20);
		yeniPanel.add(txtdurumID);

	}

	public void plakaNoOlustur() {

		plakaNo = new JLabel("Plaka No: ");
		plakaNo.setSize(100, 30);
		plakaNo.setBounds(10, 110, 150, 30);
		yeniPanel.add(plakaNo);
		txtplakaNo = new JTextField();
		txtplakaNo.setSize(250, 20);
		txtplakaNo.setBounds(125, 118, 250, 20);
		yeniPanel.add(txtplakaNo);

	}

	public void vitesOlustur() {

		vites = new JLabel("Vites: ");
		vites.setSize(100, 30);
		vites.setBounds(10, 140, 150, 30);
		yeniPanel.add(vites);
		txtvites = new JTextField();
		txtvites.setSize(250, 20);
		txtvites.setBounds(125, 148, 250, 20);
		yeniPanel.add(txtvites);

	}

	public void markaOlustur() {

		marka = new JLabel("Marka: ");
		marka.setSize(100, 30);
		marka.setBounds(10, 170, 150, 30);
		yeniPanel.add(marka);
		txtmarka = new JTextField();
		txtmarka.setSize(250, 20);
		txtmarka.setBounds(125, 178, 250, 20);
		yeniPanel.add(txtmarka);

	}

	public void modelOlustur() {

		model = new JLabel("Model: ");
		model.setSize(100, 30);
		model.setBounds(10, 200, 150, 30);
		yeniPanel.add(model);
		txtmodel = new JTextField();
		txtmodel.setSize(250, 20);
		txtmodel.setBounds(125, 208, 250, 20);
		yeniPanel.add(txtmodel);

	}

	public void fiyatOlustur() {

		fiyat = new JLabel("Fiyat: ");
		fiyat.setSize(100, 30);
		fiyat.setBounds(10, 230, 150, 30);
		yeniPanel.add(fiyat);
		txtfiyat = new JTextField();
		txtfiyat.setSize(250, 20);
		txtfiyat.setBounds(125, 238, 250, 20);
		yeniPanel.add(txtfiyat);

	}

	public void kaskoBilgisiOlustur() {

		kasko = new JLabel("Kasko Bilgisi: ");
		kasko.setSize(100, 30);
		kasko.setBounds(10, 260, 150, 30);
		yeniPanel.add(kasko);
		txtkasko = new JTextField();
		txtkasko.setSize(250, 20);
		txtkasko.setBounds(125, 268, 250, 20);
		yeniPanel.add(txtkasko);

	}

	public void kmOlustur() {

		km = new JLabel("Km Bilgisi: ");
		km.setSize(100, 30);
		km.setBounds(10, 290, 150, 30);
		yeniPanel.add(km);
		txtkm = new JTextField();
		txtkm.setSize(250, 20);
		txtkm.setBounds(125, 298, 250, 20);
		yeniPanel.add(txtkm);

	}

	public void renkOlustur() {

		renk = new JLabel("Renk Bilgisi: ");
		renk.setSize(100, 30);
		renk.setBounds(10, 320, 150, 30);
		yeniPanel.add(renk);
		txtrenk = new JTextField();
		txtrenk.setSize(250, 20);
		txtrenk.setBounds(125, 328, 250, 20);
		yeniPanel.add(txtrenk);

	}

	public void yakitTuruOlustur() {

		yakit = new JLabel("Yakit Turu: ");
		yakit.setSize(100, 30);
		yakit.setBounds(10, 350, 150, 30);
		yeniPanel.add(yakit);
		txtyakit = new JTextField();
		txtyakit.setSize(250, 20);
		txtyakit.setBounds(125, 358, 250, 20);
		yeniPanel.add(txtyakit);

	}

	public void motorGucuOlustur() {

		motor = new JLabel("Motor Gucu: ");
		motor.setSize(100, 30);
		motor.setBounds(10, 380, 150, 30);
		yeniPanel.add(motor);
		txtmotor = new JTextField();
		txtmotor.setSize(250, 20);
		txtmotor.setBounds(125, 388, 250, 20);
		yeniPanel.add(txtmotor);

	}

	public void aracDurumBilgisiOlustur() {

		durumBilgisi = new JLabel("Durum Bilgisi: ");
		durumBilgisi.setSize(100, 30);
		durumBilgisi.setBounds(10, 410, 150, 30);
		yeniPanel.add(durumBilgisi);
		txtdurumBilgisi = new JTextField();
		txtdurumBilgisi.setSize(250, 20);
		txtdurumBilgisi.setBounds(125, 418, 250, 20);
		yeniPanel.add(txtdurumBilgisi);

	}

	public void yeniKayitAracMenusu() {

		container = getContentPane();
		container.removeAll();
		yeniPanel = new JPanel(null);
		yeniPanel.setSize(1100, 600);
		yeniPanel.setBounds(0, 0, 1100, 600);
		yeniPanel.setBackground(Color.white);

		aracIDOlustur();
		durumIDOlustur();
		plakaNoOlustur();
		vitesOlustur();
		markaOlustur();
		modelOlustur();
		fiyatOlustur();
		kaskoBilgisiOlustur();
		kmOlustur();
		renkOlustur();
		yakitTuruOlustur();
		motorGucuOlustur();
		aracDurumBilgisiOlustur();

		JTextArea txtAnaSayfa = new JTextArea();
		txtAnaSayfa.setText("             ARAC BİLGİLERİ");
		Font font = new Font("Default", Font.CENTER_BASELINE, 16);
		txtAnaSayfa.setFont(font);

		// Color color=new
		// Color(115,44,123);//http://www.colorcombos.com/colors/732C7B
		// purple

		txtAnaSayfa.setForeground(Color.BLACK);
		txtAnaSayfa.setSize(460, 40);
		txtAnaSayfa.setBounds(15, 15, 460, 30);

		Color color2 = UIManager.getColor("Panel.background");

		txtAnaSayfa.setBackground(color2);
		txtAnaSayfa.setEditable(false);

		yeniPanel.add(txtAnaSayfa);
		btnKaydet = new JButton("Kaydet");
		btnKaydet.setSize(100, 30);
		btnKaydet.setBounds(271, 530, 200, 30);

		btnKaydet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// aracDurumKayit();
				aracYeniKayit();
			}

		});

		yeniPanel.add(btnKaydet);
		container.add(yeniPanel);
		invalidate();
		repaint();
	}

	public void aracYeniKayit() {
		try {
			resultArac.first();
			resultArac.moveToInsertRow();

			resultArac.updateInt("aracID", Integer.parseInt(txtaracID.getText()));
			resultArac.updateInt("durumID", Integer.parseInt(txtdurumID.getText()));
			resultArac.updateString("plakaNo", (txtplakaNo.getText()));
			resultArac.updateString("vites", txtvites.getText());
			resultArac.updateString("marka", txtmarka.getText());
			resultArac.updateString("model", txtmodel.getText());
			resultArac.updateString("fiyat", txtfiyat.getText());
			resultArac.updateString("kaskoBilgisi", txtkasko.getText());
			resultArac.updateString("km", txtkm.getText());
			resultArac.updateString("renk", txtrenk.getText());
			resultArac.updateString("yakitTuru", txtyakit.getText());
			resultArac.updateString("motorGucu", txtmotor.getText());

			resultArac.insertRow();

			resultArac.beforeFirst();

			txtaracID.setText("");
			txtdurumID.setText("");
			txtplakaNo.setText("");
			txtvites.setText("");
			txtmarka.setText("");
			txtmodel.setText("");
			txtfiyat.setText("");
			txtkasko.setText("");
			txtkm.setText("");
			txtrenk.setText("");
			txtyakit.setText("");
			txtmotor.setText("");
			JOptionPane.showMessageDialog(null, "Arac Kayıt Eklendi", "Sonuç", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, "Arac Kayıt Eklenemedi", "Sonuç", JOptionPane.PLAIN_MESSAGE);
		}
	}

	public void musteriYeniKayit() {

		try {
			resultMusteri.first();
			resultMusteri.moveToInsertRow();

			resultMusteri.updateInt("musteriID", Integer.parseInt(txtmusteriNumarasi.getText()));
			resultMusteri.updateString("telefon", txtmusteriTelefon.getText());
			resultMusteri.updateString("adres", txtmusteriAdres.getText());
			resultMusteri.updateString("mail", txtmusteriMail.getText());

			resultMusteri.insertRow();

			resultMusteri.beforeFirst();
			txtmusteriNumarasi.setText("");
			txtmusteriTelefon.setText("");
			txtmusteriAdres.setText("");
			txtmusteriMail.setText("");
			txtmusteriAracSayisi.setText("");
			JOptionPane.showMessageDialog(null, "Musteri Kayıt Eklendi", "Sonuç", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, "Musteri Kayıt Eklenemedi", "Sonuç", JOptionPane.PLAIN_MESSAGE);
		}

	}

	public void kisiYeniKayit() {
		try {
			resultKisi.first();
			resultKisi.moveToInsertRow();

			resultKisi.updateInt("kmusteriID", Integer.parseInt(txtkisiID.getText()));
			resultKisi.updateString("isim", txtkisiIsim.getText());
			resultKisi.updateString("soyisim", txtKisiSoyisim.getText());
			resultKisi.updateString("cinsiyet", txtkisiCinsiyet.getText());
			resultKisi.updateInt("yas", Integer.parseInt(txtkisiYas.getText()));
			resultKisi.updateString("TC", (txtkisiTC.getText()));
			resultKisi.updateString("ehliyetSinifi", txtkisiEhliyet.getText());

			resultKisi.insertRow();

			resultKisi.beforeFirst();

			txtkisiID.setText("");
			txtkisiIsim.setText("");
			txtKisiSoyisim.setText("");
			txtkisiCinsiyet.setText("");
			txtkisiYas.setText("");
			txtkisiTC.setText("");
			txtkisiEhliyet.setText("");
			JOptionPane.showMessageDialog(null, "Kisi Kayıt Eklendi", "Sonuç", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, "Kisi Kayıt Eklenemedi", "Sonuç", JOptionPane.PLAIN_MESSAGE);
		}
	}

	public void sirketYeniKayit() {
		try {
			resultSirket.first();
			resultSirket.moveToInsertRow();

			resultSirket.updateInt("smusteriID", Integer.parseInt(txtsirketID.getText()));
			resultSirket.updateString("sirketIsmi", txtsirketIsim.getText());

			resultSirket.insertRow();

			resultSirket.beforeFirst();
			txtsirketID.setText("");
			txtsirketIsim.setText("");

			JOptionPane.showMessageDialog(null, "Sirket Kayıt Eklendi", "Sonuç", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, "Sirket Kayıt Eklenemedi", "Sonuç", JOptionPane.PLAIN_MESSAGE);
		}

	}

	public void listelemeYap(String sqlKodu) throws SQLException {

		container = getContentPane();
		container.removeAll();
		yeniPanel = new JPanel(null);
		yeniPanel.setSize(1090, 590);
		yeniPanel.setBounds(0, 0, 1090, 590);
		yeniPanel.setBackground(Color.white);
		String[] tabloBasliklari = { "MusteriID", "Telefon", "Adres", "Mail" };

		resultMusteri = statement.executeQuery(sqlKodu);

		resultMusteri.last();

		int stokSayisi = resultMusteri.getRow();
		resultMusteri.first();
		String[][] veri = new String[stokSayisi][9];
		for (int i = 0; i < stokSayisi; i++) {
			veri[i][0] = Integer.toString(resultMusteri.getInt("musteriID"));
			veri[i][1] = resultMusteri.getString("telefon");
			veri[i][2] = resultMusteri.getString("adres");
			veri[i][3] = resultMusteri.getString("mail");

			resultMusteri.next();
		}

		tablo = new JTable(stokSayisi, 9);
		tablo.setBackground(Color.white);
		tablo.setSize(1050, 672);
		tablo.setBounds(0, 0, 1050, 672);
		// tablo.setFont(new Font("Serif", Font.BOLD, 20));
		tablo.setModel(new DefaultTableModel(veri, tabloBasliklari));
		JScrollPane panel = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		panel.setBounds(0, 0, 1095, 5672);
		panel.getViewport().add(tablo);
		// JTableHeader baslik = tablo.getTableHeader();
		// tablo.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		yeniPanel.add(panel);
		yeniPanel.setBackground(Color.lightGray);
		container.add(yeniPanel);
		invalidate();
		repaint();
	}

	public void listelemeYapKisi(String sqlKodu) throws SQLException {

		container = getContentPane();
		container.removeAll();
		yeniPanel = new JPanel(null);
		yeniPanel.setSize(1090, 590);
		yeniPanel.setBounds(0, 0, 1090, 590);
		yeniPanel.setBackground(Color.white);
		String[] tabloBasliklari = { "KisiID", "isim", "soyisim", "cinsiyet", "yas", "TC", "ehliyet" };

		resultKisi = statement2.executeQuery(sqlKodu);

		resultKisi.last();

		int stokSayisi = resultKisi.getRow();
		resultKisi.first();
		String[][] veri = new String[stokSayisi][9];
		for (int i = 0; i < stokSayisi; i++) {
			veri[i][0] = Integer.toString(resultKisi.getInt("kmusteriID"));
			veri[i][1] = resultKisi.getString("isim");
			veri[i][2] = resultKisi.getString("soyisim");
			veri[i][3] = resultKisi.getString("cinsiyet");
			veri[i][4] = Integer.toString(resultKisi.getInt("yas"));
			veri[i][5] = (resultKisi.getString("TC"));
			veri[i][6] = resultKisi.getString("ehliyetSinifi");
			resultKisi.next();
		}

		tabloKisi = new JTable(stokSayisi, 9);
		tabloKisi.setBackground(Color.white);
		tabloKisi.setSize(1050, 672);
		tabloKisi.setBounds(0, 0, 1050, 672);
		// tablo.setFont(new Font("Serif", Font.BOLD, 20));
		tabloKisi.setModel(new DefaultTableModel(veri, tabloBasliklari));
		JScrollPane panel = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		panel.setBounds(0, 0, 1095, 5672);
		panel.getViewport().add(tabloKisi);
		// JTableHeader baslik = tabloKisi.getTableHeader();
		// tablo.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		yeniPanel.add(panel);
		yeniPanel.setBackground(Color.lightGray);
		container.add(yeniPanel);
		invalidate();
		repaint();
	}

	public void icerikArama(String etiketMesaj) {

		container = getContentPane();
		container.removeAll();
		yeniPanel = new JPanel(null);
		yeniPanel.setSize(1100, 590);
		yeniPanel.setBounds(0, 0, 1100, 590);
		yeniPanel.setBackground(Color.white);
		JLabel guncelle = new JLabel(etiketMesaj);
		guncelle.setSize(200, 30);
		guncelle.setBounds(10, 50, 457, 30);
		yeniPanel.add(guncelle);
		txtGuncelle = new JTextField();
		txtGuncelle.setSize(50, 30);
		txtGuncelle.setBounds(400, 55, 200, 20);
		yeniPanel.add(txtGuncelle);
		guncelleSec = new JButton("Seç");
		guncelleSec.setSize(70, 19);
		guncelleSec.setBounds(650, 55, 70, 19);
		yeniPanel.add(guncelleSec);
		container.add(yeniPanel);
		invalidate();
		repaint();

	}

	public void listelemeYapSirket(String sqlKodu) throws SQLException {

		container = getContentPane();
		container.removeAll();
		yeniPanel = new JPanel(null);
		yeniPanel.setSize(1090, 590);
		yeniPanel.setBounds(0, 0, 1090, 590);
		yeniPanel.setBackground(Color.white);
		String[] tabloBasliklari = { "SirketID", "Sirket Ismi" };

		resultSirket = statement3.executeQuery(sqlKodu);

		resultSirket.last();

		int stokSayisi = resultSirket.getRow();
		resultSirket.first();
		String[][] veri = new String[stokSayisi][9];
		for (int i = 0; i < stokSayisi; i++) {
			veri[i][0] = Integer.toString(resultSirket.getInt("smusteriID"));
			veri[i][1] = resultSirket.getString("sirketIsmi");

			resultSirket.next();
		}

		tabloSirket = new JTable(stokSayisi, 9);
		tabloSirket.setBackground(Color.white);
		tabloSirket.setSize(1050, 672);
		tabloSirket.setBounds(0, 0, 1050, 672);
		// tablo.setFont(new Font("Serif", Font.BOLD, 20));
		tabloSirket.setModel(new DefaultTableModel(veri, tabloBasliklari));
		JScrollPane panel = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		panel.setBounds(0, 0, 1095, 5672);
		panel.getViewport().add(tabloSirket);
		// JTableHeader baslik = tabloSirket.getTableHeader();
		// tablo.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		yeniPanel.add(panel);
		yeniPanel.setBackground(Color.lightGray);
		container.add(yeniPanel);
		invalidate();
		repaint();
	}

	public void sirketSil() {

		icerikArama("Silinecek Sirketin ID Numarası");
		guncelleSec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					resultMusteri2 = statement.executeQuery(
							"SELECT * FROM sirket WHERE smusteriID = " + Integer.parseInt(txtGuncelle.getText()));
					resultMusteri2.first();
					resultMusteri2.deleteRow();
					resultMusteri2 = statement.executeQuery("SELECT * FROM sirket");

					resultMusteri2 = statement.executeQuery(
							"SELECT * FROM musteri WHERE musteriID = " + Integer.parseInt(txtGuncelle.getText()));
					resultMusteri2.first();
					resultMusteri2.deleteRow();
					resultMusteri2 = statement.executeQuery("SELECT * FROM musteri");

					JOptionPane.showMessageDialog(null, "Kayıt Silindi", "Sonuç", JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException ex) {
					Logger.getLogger(arabaKirala.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		});

		/*
		 *
		 * JLabel tumSil = new JLabel("Tüm Musterileri sil");
		 * tumSil.setSize(100, 20); tumSil.setBounds(10, 80, 350, 20);
		 * yeniPanel.add(tumSil); JButton btnTumKayitlariSil = new JButton(
		 * "Hepsini Sil"); btnTumKayitlariSil.setSize(100, 20);
		 * btnTumKayitlariSil.setBounds(300, 80, 150, 20);
		 * yeniPanel.add(btnTumKayitlariSil);
		 * btnTumKayitlariSil.addActionListener(new ActionListener() { public
		 * void actionPerformed(ActionEvent e) { try { statement.executeUpdate(
		 * "DELETE FROM musteri"); } catch (SQLException ex) {
		 * Logger.getLogger(arabaKirala.class.getName()).log(Level.SEVERE, null,
		 * ex); } } });
		 */

	}

	public void kisiSil() {

		icerikArama("Silinecek Kisinin ID Numarası");
		guncelleSec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					resultMusteri2 = statement.executeQuery(
							"SELECT * FROM kisi WHERE kmusteriID = " + Integer.parseInt(txtGuncelle.getText()));
					resultMusteri2.first();
					resultMusteri2.deleteRow();
					resultMusteri2 = statement.executeQuery("SELECT * FROM kisi");

					resultMusteri2 = statement.executeQuery(
							"SELECT * FROM musteri WHERE musteriID = " + Integer.parseInt(txtGuncelle.getText()));
					resultMusteri2.first();
					resultMusteri2.deleteRow();
					resultMusteri2 = statement.executeQuery("SELECT * FROM musteri");

					JOptionPane.showMessageDialog(null, "Kayıt Silindi", "Sonuç", JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException ex) {
					Logger.getLogger(arabaKirala.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		});
	}

	public void listelemeYapArac(String sqlKodu) throws SQLException {

		container = getContentPane();
		container.removeAll();
		yeniPanel = new JPanel(null);
		yeniPanel.setSize(1090, 590);
		yeniPanel.setBounds(0, 0, 1090, 590);
		yeniPanel.setBackground(Color.white);
		String[] tabloBasliklari = { "aracID", "durumID", "plakaNo", "vites", "marka", "model", "fiyat", "kasko", "km",
				"renk", "yakit", "motor" };

		resultArac = statement4.executeQuery(sqlKodu);

		resultArac.last();

		int stokSayisi = resultArac.getRow();
		resultArac.first();
		String[][] veri = new String[stokSayisi][15];
		for (int i = 0; i < stokSayisi; i++) {
			veri[i][0] = Integer.toString(resultArac.getInt("aracID"));
			veri[i][1] = Integer.toString(resultArac.getInt("durumID"));
			veri[i][2] = resultArac.getString("plakaNo");
			veri[i][3] = resultArac.getString("vites");
			veri[i][4] = resultArac.getString("marka");
			veri[i][5] = resultArac.getString("model");
			veri[i][6] = resultArac.getString("fiyat");
			veri[i][7] = resultArac.getString("kaskoBilgisi");
			veri[i][8] = resultArac.getString("km");
			veri[i][9] = resultArac.getString("renk");
			veri[i][10] = resultArac.getString("yakitTuru");
			veri[i][11] = resultArac.getString("motorGucu");

			resultArac.next();
		}

		tabloArac = new JTable(stokSayisi, 9);
		tabloArac.setBackground(Color.white);
		tabloArac.setSize(1050, 672);
		tabloArac.setBounds(0, 0, 1050, 672);
		// tablo.setFont(new Font("Serif", Font.BOLD, 20));
		tabloArac.setModel(new DefaultTableModel(veri, tabloBasliklari));
		JScrollPane panel = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		panel.setBounds(0, 0, 1095, 5672);
		panel.getViewport().add(tabloArac);
		// JTableHeader baslik = tabloArac.getTableHeader();
		// tablo.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		yeniPanel.add(panel);
		yeniPanel.setBackground(Color.lightGray);
		container.add(yeniPanel);
		invalidate();
		repaint();
	}

	public void arabaSil() {

		icerikArama("Silinecek Arabanın ID Numarası");
		guncelleSec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					resultArac2 = statement.executeQuery(
							"SELECT * FROM araba WHERE arabaID = " + Integer.parseInt(txtGuncelle.getText()));
					resultArac2.first();
					resultArac2.deleteRow();
					resultArac2 = statement.executeQuery("SELECT * FROM araba");

					resultArac2 = statement.executeQuery(
							"SELECT * FROM arac WHERE aracID = " + Integer.parseInt(txtGuncelle.getText()));
					resultArac2.first();
					resultArac2.deleteRow();
					resultArac2 = statement.executeQuery("SELECT * FROM arac");

					JOptionPane.showMessageDialog(null, "Kayıt Silindi", "Sonuç", JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException ex) {
					Logger.getLogger(arabaKirala.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		});
	}

	public void isMakinesiSil() {

		icerikArama("Silinecek IsMakinesinin ID Numarası");
		guncelleSec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					resultArac2 = statement.executeQuery(
							"SELECT * FROM ismakinesi WHERE ismakinesiID = " + Integer.parseInt(txtGuncelle.getText()));
					resultArac2.first();
					resultArac2.deleteRow();
					resultArac2 = statement.executeQuery("SELECT * FROM ismakinesi");

					resultArac2 = statement.executeQuery(
							"SELECT * FROM arac WHERE aracID = " + Integer.parseInt(txtGuncelle.getText()));
					resultArac2.first();
					resultArac2.deleteRow();
					resultArac2 = statement.executeQuery("SELECT * FROM arac");

					JOptionPane.showMessageDialog(null, "Kayıt Silindi", "Sonuç", JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException ex) {
					Logger.getLogger(arabaKirala.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		});
	}

	public void durumSil() {

		icerikArama("Silinecek Durum ID");
		guncelleSec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					resultDurum = statement.executeQuery(
							"SELECT * FROM durum WHERE aracDurumID = " + Integer.parseInt(txtGuncelle.getText()));
					resultDurum.first();
					resultDurum.deleteRow();
					resultDurum = statement.executeQuery("SELECT * FROM durum");

					JOptionPane.showMessageDialog(null, "Kayıt Silindi", "Sonuç", JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException ex) {
					Logger.getLogger(arabaKirala.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		});
	}

	public void arabaIDOlustur() {

		arabaID = new JLabel("Araba ID: ");
		arabaID.setSize(100, 30);
		arabaID.setBounds(10, 50, 150, 30);
		yeniPanel.add(arabaID);
		txtarabaID = new JTextField();
		txtarabaID.setSize(250, 20);
		txtarabaID.setBounds(125, 58, 250, 20);
		yeniPanel.add(txtarabaID);

	}

	public void kapiSayisiOlustur() {

		kapiSayisi = new JLabel("Kapı Sayisi: ");
		kapiSayisi.setSize(100, 30);
		kapiSayisi.setBounds(10, 80, 150, 30);
		yeniPanel.add(kapiSayisi);
		txtkapiSayisi = new JTextField();
		txtkapiSayisi.setSize(250, 20);
		txtkapiSayisi.setBounds(125, 88, 250, 20);
		yeniPanel.add(txtkapiSayisi);

	}

	public void yeniKayitArabaMenusu() {

		container = getContentPane();
		container.removeAll();
		yeniPanel = new JPanel(null);
		yeniPanel.setSize(1100, 600);
		yeniPanel.setBounds(0, 0, 1100, 600);

		yeniPanel.setBackground(Color.white);

		arabaIDOlustur();
		kapiSayisiOlustur();

		JTextArea txtAnaSayfa = new JTextArea();
		txtAnaSayfa.setText("             ARABA BİLGİLERİ");
		Font font = new Font("Default", Font.CENTER_BASELINE, 16);
		txtAnaSayfa.setFont(font);

		// Color color=new
		// Color(115,44,123);//http://www.colorcombos.com/colors/732C7B
		// purple

		txtAnaSayfa.setForeground(Color.BLACK);
		txtAnaSayfa.setSize(460, 40);
		txtAnaSayfa.setBounds(15, 15, 460, 30);

		Color color2 = UIManager.getColor("Panel.background");

		txtAnaSayfa.setBackground(color2);
		txtAnaSayfa.setEditable(false);

		yeniPanel.add(txtAnaSayfa);
		btnKaydet = new JButton(" Kaydet");
		btnKaydet.setSize(100, 30);
		btnKaydet.setBounds(271, 530, 200, 30);

		btnKaydet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				arabaYeniKayit();
			}

		});

		yeniPanel.add(btnKaydet);
		container.add(yeniPanel);
		invalidate();
		repaint();
	}

	public void arabaYeniKayit() {
		try {
			resultAraba.first();
			resultAraba.moveToInsertRow();

			resultAraba.updateInt("arabaID", Integer.parseInt(txtarabaID.getText()));
			resultAraba.updateString("kapiSayisi", txtkapiSayisi.getText());

			resultAraba.insertRow();

			resultAraba.beforeFirst();
			txtarabaID.setText("");
			txtkapiSayisi.setText("");

			JOptionPane.showMessageDialog(null, "Araba Kayıt Eklendi", "Sonuç", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, "Araba Kayıt Eklenemedi", "Sonuç", JOptionPane.PLAIN_MESSAGE);
		}

	}

	public void listelemeYapAraba(String sqlKodu) throws SQLException {

		container = getContentPane();
		container.removeAll();
		yeniPanel = new JPanel(null);
		yeniPanel.setSize(1090, 590);
		yeniPanel.setBounds(0, 0, 1090, 590);
		yeniPanel.setBackground(Color.white);
		String[] tabloBasliklari = { "arabaID", "kapiSayisi" };

		resultAraba = statement6.executeQuery(sqlKodu);

		resultAraba.last();

		int stokSayisi = resultAraba.getRow();
		resultAraba.first();
		String[][] veri = new String[stokSayisi][9];
		for (int i = 0; i < stokSayisi; i++) {
			veri[i][0] = Integer.toString(resultAraba.getInt("arabaID"));
			veri[i][1] = resultAraba.getString("kapiSayisi");

			resultAraba.next();
		}

		tabloAraba = new JTable(stokSayisi, 9);
		tabloAraba.setBackground(Color.white);
		tabloAraba.setSize(1050, 672);
		tabloAraba.setBounds(0, 0, 1050, 672);
		// tablo.setFont(new Font("Serif", Font.BOLD, 20));
		tabloAraba.setModel(new DefaultTableModel(veri, tabloBasliklari));
		JScrollPane panel = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		panel.setBounds(0, 0, 1095, 5672);
		panel.getViewport().add(tabloAraba);
		// JTableHeader baslik = tabloAraba.getTableHeader();
		// tablo.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		yeniPanel.add(panel);
		yeniPanel.setBackground(Color.lightGray);
		container.add(yeniPanel);
		invalidate();
		repaint();
	}

	public void ismakinesiIDOlustur() {

		isID = new JLabel("Is Makinesi ID: ");
		isID.setSize(100, 30);
		isID.setBounds(10, 50, 150, 30);
		yeniPanel.add(isID);
		txtisID = new JTextField();
		txtisID.setSize(250, 20);
		txtisID.setBounds(125, 58, 250, 20);
		yeniPanel.add(txtisID);

	}

	public void tonajOlustur() {

		tonaj = new JLabel("Tonaj: ");
		tonaj.setSize(100, 30);
		tonaj.setBounds(10, 80, 150, 30);
		yeniPanel.add(tonaj);
		txttonaj = new JTextField();
		txttonaj.setSize(250, 20);
		txttonaj.setBounds(125, 88, 250, 20);
		yeniPanel.add(txttonaj);

	}

	public void yeniKayitIsMakinesiMenusu() {

		container = getContentPane();
		container.removeAll();
		yeniPanel = new JPanel(null);
		yeniPanel.setSize(1100, 600);
		yeniPanel.setBounds(0, 0, 1100, 600);

		yeniPanel.setBackground(Color.white);

		ismakinesiIDOlustur();
		tonajOlustur();

		JTextArea txtAnaSayfa = new JTextArea();
		txtAnaSayfa.setText("             İs MAKİNESİ BİLGİLERİ");
		Font font = new Font("Default", Font.CENTER_BASELINE, 16);
		txtAnaSayfa.setFont(font);

		// Color color=new
		// Color(115,44,123);//http://www.colorcombos.com/colors/732C7B
		// purple

		txtAnaSayfa.setForeground(Color.BLACK);
		txtAnaSayfa.setSize(460, 40);
		txtAnaSayfa.setBounds(15, 15, 460, 30);

		Color color2 = UIManager.getColor("Panel.background");

		txtAnaSayfa.setBackground(color2);
		txtAnaSayfa.setEditable(false);

		yeniPanel.add(txtAnaSayfa);
		btnKaydet = new JButton(" Kaydet");
		btnKaydet.setSize(100, 30);
		btnKaydet.setBounds(271, 530, 200, 30);

		btnKaydet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isMakinesiYeniKayit();
			}

		});

		yeniPanel.add(btnKaydet);
		container.add(yeniPanel);
		invalidate();
		repaint();
	}

	public void isMakinesiYeniKayit() {
		try {
			resultIsMakinesi.first();
			resultIsMakinesi.moveToInsertRow();

			resultIsMakinesi.updateInt("ismakinesiID", Integer.parseInt(txtisID.getText()));
			resultIsMakinesi.updateString("tonaj", txttonaj.getText());

			resultIsMakinesi.insertRow();

			resultIsMakinesi.beforeFirst();
			txtisID.setText("");
			txttonaj.setText("");

			JOptionPane.showMessageDialog(null, "is makinesi Kayıt Eklendi", "Sonuç", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, "is makinesi Kayıt Eklenemedi", "Sonuç", JOptionPane.PLAIN_MESSAGE);
		}

	}

	public void listelemeYapIsMakinesi(String sqlKodu) throws SQLException {

		container = getContentPane();
		container.removeAll();
		yeniPanel = new JPanel(null);
		yeniPanel.setSize(1090, 590);
		yeniPanel.setBounds(0, 0, 1090, 590);
		yeniPanel.setBackground(Color.white);
		String[] tabloBasliklari = { "arabaID", "kapiSayisi" };

		resultIsMakinesi = statement7.executeQuery(sqlKodu);

		resultIsMakinesi.last();

		int stokSayisi = resultIsMakinesi.getRow();
		resultIsMakinesi.first();
		String[][] veri = new String[stokSayisi][9];
		for (int i = 0; i < stokSayisi; i++) {
			veri[i][0] = Integer.toString(resultIsMakinesi.getInt("ismakinesiID"));
			veri[i][1] = resultIsMakinesi.getString("tonaj");

			resultIsMakinesi.next();
		}

		tabloIsMakinesi = new JTable(stokSayisi, 9);
		tabloIsMakinesi.setBackground(Color.white);
		tabloIsMakinesi.setSize(1050, 672);
		tabloIsMakinesi.setBounds(0, 0, 1050, 672);
		// tablo.setFont(new Font("Serif", Font.BOLD, 20));
		tabloIsMakinesi.setModel(new DefaultTableModel(veri, tabloBasliklari));
		JScrollPane panel = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		panel.setBounds(0, 0, 1095, 5672);
		panel.getViewport().add(tabloIsMakinesi);
		// JTableHeader baslik = tabloIsMakinesi.getTableHeader();
		// tablo.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		yeniPanel.add(panel);
		yeniPanel.setBackground(Color.lightGray);
		container.add(yeniPanel);
		invalidate();
		repaint();
	}

	public void guncellemeMenusuMusteri() {

		icerikArama("Güncellenecek Musteri ID");
		guncelleSec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					container = getContentPane();
					container.removeAll();
					yeniPanel = new JPanel(null);
					yeniPanel.setSize(1100, 590);
					yeniPanel.setBounds(0, 0, 1100, 590);
					yeniPanel.setBackground(Color.white);
					resultMusteri = statement.executeQuery(
							"SELECT * FROM musteri WHERE musteriID =  " + Integer.parseInt(txtGuncelle.getText()));
					resultMusteri.first();

					musteriNumarasiOlustur();
					txtmusteriNumarasi.setEditable(false);
					txtmusteriNumarasi.setText(txtGuncelle.getText());

					musteriTelefonOlustur();
					txtmusteriTelefon.setText(resultMusteri.getString("telefon"));
					musteriAdresOlustur();
					txtmusteriAdres.setText(resultMusteri.getString("adres"));
					musteriMailOlustur();
					txtmusteriMail.setText(resultMusteri.getString("mail"));

					JButton btnGuncelle = new JButton("Güncelle");
					btnGuncelle.setSize(70, 30);
					btnGuncelle.setBounds(271, 330, 100, 30);
					yeniPanel.add(btnGuncelle);
					btnGuncelle.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try {
								resultMusteri.updateString("telefon", (txtmusteriTelefon.getText()));
								resultMusteri.updateString("adres", (txtmusteriAdres.getText()));
								resultMusteri.updateString("mail", txtmusteriMail.getText());

								resultMusteri.updateRow();
								resultMusteri = statement.executeQuery("SELECT * FROM musteri");
								JOptionPane.showMessageDialog(null, "Kayıt Güncellendi", "Sonuç",
										JOptionPane.INFORMATION_MESSAGE);
							} catch (SQLException ex) {
								JOptionPane.showConfirmDialog(null, "Güncelleme sırasında hata olustu.", "Sonuc",
										JOptionPane.PLAIN_MESSAGE);
							}
						}
					});
					container.add(yeniPanel);
					invalidate();
					repaint();
				} catch (SQLException ex) {
					Logger.getLogger(arabaKirala.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		});

	}

	public void guncellemeMenusuKisi() {

		icerikArama("Güncellenecek Kisi ID");
		guncelleSec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					container = getContentPane();
					container.removeAll();
					yeniPanel = new JPanel(null);
					yeniPanel.setSize(1100, 590);
					yeniPanel.setBounds(0, 0, 1100, 590);
					yeniPanel.setBackground(Color.white);
					resultKisi = statement2.executeQuery(
							"SELECT * FROM kisi WHERE kmusteriID =  " + Integer.parseInt(txtGuncelle.getText()));
					resultKisi.first();

					kisiNumarasiOlustur();
					txtkisiID.setEditable(false);
					txtkisiID.setText(txtGuncelle.getText());

					kisiIsimOlustur();
					txtkisiIsim.setText(resultKisi.getString("isim"));
					kisiSoyisimOlustur();
					txtKisiSoyisim.setText(resultKisi.getString("soyisim"));
					kisiCinsiyetOlustur();
					txtkisiCinsiyet.setText(resultKisi.getString("cinsiyet"));
					kisiYasOlustur();
					txtkisiYas.setText(resultKisi.getString("yas"));
					kisiTCOlustur();
					txtkisiTC.setText(resultKisi.getString("TC"));
					kisiEhliyetOlustur();
					txtkisiEhliyet.setText(resultKisi.getString("ehliyetSinifi"));

					JButton btnGuncelle = new JButton("Güncelle");
					btnGuncelle.setSize(70, 30);
					btnGuncelle.setBounds(271, 330, 100, 30);
					yeniPanel.add(btnGuncelle);
					btnGuncelle.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try {
								resultKisi.updateString("isim", (txtkisiIsim.getText()));
								resultKisi.updateString("soyisim", (txtKisiSoyisim.getText()));
								resultKisi.updateString("cinsiyet", txtkisiCinsiyet.getText());
								resultKisi.updateInt("yas", Integer.parseInt((txtkisiYas.getText())));
								resultKisi.updateString("TC", ((txtkisiTC.getText())));
								resultKisi.updateString("ehliyetSinifi", txtkisiEhliyet.getText());

								resultKisi.updateRow();
								resultKisi = statement2.executeQuery("SELECT * FROM kisi");
								JOptionPane.showMessageDialog(null, "Kayıt Güncellendi", "Sonuç",
										JOptionPane.INFORMATION_MESSAGE);
							} catch (SQLException ex) {
								JOptionPane.showConfirmDialog(null, "Güncelleme sırasında hata olustu.", "Sonuc",
										JOptionPane.PLAIN_MESSAGE);
							}
						}
					});
					container.add(yeniPanel);
					invalidate();
					repaint();
				} catch (SQLException ex) {
					Logger.getLogger(arabaKirala.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		});

	}

	public void guncellemeMenusuSirket() {

		icerikArama("Güncellenecek Sirket ID");
		guncelleSec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					container = getContentPane();
					container.removeAll();
					yeniPanel = new JPanel(null);
					yeniPanel.setSize(1100, 590);
					yeniPanel.setBounds(0, 0, 1100, 590);
					yeniPanel.setBackground(Color.white);
					resultSirket = statement2.executeQuery(
							"SELECT * FROM sirket WHERE smusteriID =  " + Integer.parseInt(txtGuncelle.getText()));
					resultSirket.first();

					sirketNumarasiOlustur();
					txtsirketID.setEditable(false);
					txtsirketID.setText(txtGuncelle.getText());
					sirketIsmiOlustur();
					txtsirketIsim.setText(resultSirket.getString("sirketIsmi"));

					JButton btnGuncelle = new JButton("Güncelle");
					btnGuncelle.setSize(70, 30);
					btnGuncelle.setBounds(271, 330, 100, 30);
					yeniPanel.add(btnGuncelle);
					btnGuncelle.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try {
								resultSirket.updateString("sirketIsmi", (txtsirketIsim.getText()));

								resultSirket.updateRow();
								resultSirket = statement3.executeQuery("SELECT * FROM sirket");
								JOptionPane.showMessageDialog(null, "Kayıt Güncellendi", "Sonuç",
										JOptionPane.INFORMATION_MESSAGE);
							} catch (SQLException ex) {
								JOptionPane.showConfirmDialog(null, "Güncelleme sırasında hata olustu.", "Sonuc",
										JOptionPane.PLAIN_MESSAGE);
							}
						}
					});
					container.add(yeniPanel);
					invalidate();
					repaint();
				} catch (SQLException ex) {
					Logger.getLogger(arabaKirala.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		});

	}

	public void guncellemeMenusuArac() {

		icerikArama("Güncellenecek Arac ID");
		guncelleSec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					container = getContentPane();
					container.removeAll();
					yeniPanel = new JPanel(null);
					yeniPanel.setSize(1100, 590);
					yeniPanel.setBounds(0, 0, 1100, 590);
					yeniPanel.setBackground(Color.white);
					resultArac = statement2.executeQuery(
							"SELECT * FROM arac WHERE aracID =  " + Integer.parseInt(txtGuncelle.getText()));
					resultArac.first();

					aracIDOlustur();
					txtaracID.setEditable(false);
					txtaracID.setText(txtGuncelle.getText());
					durumIDOlustur();
					txtdurumID.setText(resultArac.getString("durumID"));
					plakaNoOlustur();
					txtplakaNo.setText(resultArac.getString("plakaNo"));
					vitesOlustur();
					txtvites.setText(resultArac.getString("vites"));
					markaOlustur();
					txtmarka.setText(resultArac.getString("marka"));
					modelOlustur();
					txtmodel.setText(resultArac.getString("model"));
					fiyatOlustur();
					txtfiyat.setText(resultArac.getString("fiyat"));
					kaskoBilgisiOlustur();
					txtkasko.setText(resultArac.getString("kaskoBilgisi"));
					kmOlustur();
					txtkm.setText(resultArac.getString("km"));
					renkOlustur();
					txtrenk.setText(resultArac.getString("renk"));
					yakitTuruOlustur();
					txtyakit.setText(resultArac.getString("yakitTuru"));
					motorGucuOlustur();
					txtmotor.setText(resultArac.getString("motorGucu"));

					JButton btnGuncelle = new JButton("Güncelle");
					btnGuncelle.setSize(70, 30);
					btnGuncelle.setBounds(571, 330, 100, 30);
					yeniPanel.add(btnGuncelle);
					btnGuncelle.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try {

								resultArac.updateInt("durumID", Integer.parseInt((txtdurumID.getText())));
								resultArac.updateString("plakaNo", ((txtplakaNo.getText())));
								resultArac.updateString("vites", (txtvites.getText()));
								resultArac.updateString("marka", (txtmarka.getText()));
								resultArac.updateString("model", (txtmodel.getText()));
								resultArac.updateString("fiyat", (txtfiyat.getText()));
								resultArac.updateString("kaskoBilgisi", (txtkasko.getText()));
								resultArac.updateString("km", (txtkm.getText()));
								resultArac.updateString("renk", (txtrenk.getText()));
								resultArac.updateString("yakitTuru", (txtyakit.getText()));
								resultArac.updateString("motorGucu", (txtmotor.getText()));

								resultArac.updateRow();
								resultArac = statement4.executeQuery("SELECT * FROM arac");
								JOptionPane.showMessageDialog(null, "Kayıt Güncellendi", "Sonuç",
										JOptionPane.INFORMATION_MESSAGE);
							} catch (SQLException ex) {
								JOptionPane.showConfirmDialog(null, "Güncelleme sırasında hata olustu.", "Sonuc",
										JOptionPane.PLAIN_MESSAGE);
							}
						}
					});
					container.add(yeniPanel);
					invalidate();
					repaint();
				} catch (SQLException ex) {
					Logger.getLogger(arabaKirala.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		});

	}

	public void guncellemeMenusuAraba() {

		icerikArama("Güncellenecek Araba ID");
		guncelleSec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					container = getContentPane();
					container.removeAll();
					yeniPanel = new JPanel(null);
					yeniPanel.setSize(1100, 590);
					yeniPanel.setBounds(0, 0, 1100, 590);
					yeniPanel.setBackground(Color.white);
					resultAraba = statement6.executeQuery(
							"SELECT * FROM araba WHERE arabaID =  " + Integer.parseInt(txtGuncelle.getText()));
					resultAraba.first();

					arabaIDOlustur();
					txtarabaID.setEditable(false);
					txtarabaID.setText(txtGuncelle.getText());
					kapiSayisiOlustur();
					txtkapiSayisi.setText(resultAraba.getString("kapiSayisi"));

					JButton btnGuncelle = new JButton("Güncelle");
					btnGuncelle.setSize(70, 30);
					btnGuncelle.setBounds(571, 330, 100, 30);
					yeniPanel.add(btnGuncelle);
					btnGuncelle.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try {

								resultAraba.updateString("kapiSayisi", (txtkapiSayisi.getText()));

								resultAraba.updateRow();
								resultAraba = statement5.executeQuery("SELECT * FROM araba");
								JOptionPane.showMessageDialog(null, "Kayıt Güncellendi", "Sonuç",
										JOptionPane.INFORMATION_MESSAGE);
							} catch (SQLException ex) {
								JOptionPane.showConfirmDialog(null, "Güncelleme sırasında hata olustu.", "Sonuc",
										JOptionPane.PLAIN_MESSAGE);
							}
						}
					});
					container.add(yeniPanel);
					invalidate();
					repaint();
				} catch (SQLException ex) {
					Logger.getLogger(arabaKirala.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		});

	}

	public void guncellemeMenusuIsMakinesi() {

		icerikArama("Güncellenecek Is Makinesi ID");
		guncelleSec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					container = getContentPane();
					container.removeAll();
					yeniPanel = new JPanel(null);
					yeniPanel.setSize(1100, 590);
					yeniPanel.setBounds(0, 0, 1100, 590);
					yeniPanel.setBackground(Color.white);
					resultIsMakinesi = statement6.executeQuery("SELECT * FROM ismakinesi WHERE ismakinesiID =  "
							+ Integer.parseInt(txtGuncelle.getText()));
					resultIsMakinesi.first();

					ismakinesiIDOlustur();
					txtisID.setEditable(false);
					txtisID.setText(txtGuncelle.getText());
					tonajOlustur();
					txttonaj.setText(resultIsMakinesi.getString("tonaj"));

					JButton btnGuncelle = new JButton("Güncelle");
					btnGuncelle.setSize(70, 30);
					btnGuncelle.setBounds(571, 330, 100, 30);
					yeniPanel.add(btnGuncelle);
					btnGuncelle.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try {

								resultIsMakinesi.updateString("tonaj", (txttonaj.getText()));

								resultIsMakinesi.updateRow();
								resultIsMakinesi = statement7.executeQuery("SELECT * FROM ismakinesi");
								JOptionPane.showMessageDialog(null, "Kayıt Güncellendi", "Sonuç",
										JOptionPane.INFORMATION_MESSAGE);
							} catch (SQLException ex) {
								JOptionPane.showConfirmDialog(null, "Güncelleme sırasında hata olustu.", "Sonuc",
										JOptionPane.PLAIN_MESSAGE);
							}
						}
					});
					container.add(yeniPanel);
					invalidate();
					repaint();
				} catch (SQLException ex) {
					Logger.getLogger(arabaKirala.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		});

	}

	public void guncellemeMenusuDurum() {

		icerikArama("Güncellenecek Durum ID");
		guncelleSec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					container = getContentPane();
					container.removeAll();
					yeniPanel = new JPanel(null);
					yeniPanel.setSize(1100, 590);
					yeniPanel.setBounds(0, 0, 1100, 590);
					yeniPanel.setBackground(Color.white);
					resultDurum = statement6.executeQuery(
							"SELECT * FROM durum WHERE aracDurumID =  " + Integer.parseInt(txtGuncelle.getText()));
					resultDurum.first();

					aracDurumIDOlustur();
					txtaracDurumID.setEditable(false);
					txtaracDurumID.setText(txtGuncelle.getText());

					durumBilgisiOlustur();
					txtdurum.setText(resultDurum.getString("durumBilgisi"));

					JButton btnGuncelle = new JButton("Güncelle");
					btnGuncelle.setSize(70, 30);
					btnGuncelle.setBounds(571, 330, 100, 30);
					yeniPanel.add(btnGuncelle);
					btnGuncelle.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try {

								resultDurum.updateString("durumBilgisi", (txtdurum.getText()));

								resultDurum.updateRow();
								resultDurum = statement5.executeQuery("SELECT * FROM durum");
								JOptionPane.showMessageDialog(null, "Kayıt Güncellendi", "Sonuç",
										JOptionPane.INFORMATION_MESSAGE);
							} catch (SQLException ex) {
								JOptionPane.showConfirmDialog(null, "Güncelleme sırasında hata olustu.", "Sonuc",
										JOptionPane.PLAIN_MESSAGE);
							}
						}
					});
					container.add(yeniPanel);
					invalidate();
					repaint();
				} catch (SQLException ex) {
					Logger.getLogger(arabaKirala.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		});

	}

	public void kiralananaracIDOlustur() {

		KiralananaracID = new JLabel("Kiralanan Arac ID: ");
		KiralananaracID.setSize(100, 30);
		KiralananaracID.setBounds(10, 50, 200, 30);
		yeniPanel.add(KiralananaracID);
		txtKiralananaracID = new JTextField();
		txtKiralananaracID.setSize(250, 20);
		txtKiralananaracID.setBounds(265, 58, 250, 20);
		yeniPanel.add(txtKiralananaracID);

	}

	public void aracKiralayanmusteriIDOlustur() {

		AracKiralayanMusteriID = new JLabel("Arac Kiralayan Musteri ID: ");
		AracKiralayanMusteriID.setSize(100, 30);
		AracKiralayanMusteriID.setBounds(10, 80, 200, 30);
		yeniPanel.add(AracKiralayanMusteriID);
		txtAracKiralayanMusteriID = new JTextField();
		txtAracKiralayanMusteriID.setSize(250, 20);
		txtAracKiralayanMusteriID.setBounds(265, 88, 250, 20);
		yeniPanel.add(txtAracKiralayanMusteriID);

	}

	public void kirala() {

		container = getContentPane();
		container.removeAll();
		yeniPanel = new JPanel(null);
		yeniPanel.setSize(1100, 600);
		yeniPanel.setBounds(0, 0, 1100, 600);
		yeniPanel.setBackground(Color.white);

		kiralananaracIDOlustur();
		aracKiralayanmusteriIDOlustur();

		JTextArea txtAnaSayfa = new JTextArea();
		txtAnaSayfa.setText("             ARAC KİRALA");
		Font font = new Font("Default", Font.CENTER_BASELINE, 16);
		txtAnaSayfa.setFont(font);

		// Color color=new
		// Color(115,44,123);//http://www.colorcombos.com/colors/732C7B
		// purple

		txtAnaSayfa.setForeground(Color.BLACK);
		txtAnaSayfa.setSize(460, 40);
		txtAnaSayfa.setBounds(15, 15, 460, 30);

		Color color2 = UIManager.getColor("Panel.background");

		txtAnaSayfa.setBackground(color2);
		txtAnaSayfa.setEditable(false);

		yeniPanel.add(txtAnaSayfa);
		btnKaydet = new JButton("Kaydet");
		btnKaydet.setSize(100, 30);
		btnKaydet.setBounds(271, 530, 200, 30);

		btnKaydet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aracKirala();
			}

		});

		yeniPanel.add(btnKaydet);
		container.add(yeniPanel);
		invalidate();
		repaint();
	}

	public void aracKirala() {

		try {
			resultKira.first();
			resultKira.moveToInsertRow();

			resultKira.updateInt("kiralananAracID", Integer.parseInt(txtKiralananaracID.getText()));
			resultKira.updateInt("kiralayanID", Integer.parseInt(txtAracKiralayanMusteriID.getText()));

			resultKira.insertRow();

			resultKira.beforeFirst();
			txtKiralananaracID.setText("");
			txtAracKiralayanMusteriID.setText("");

			JOptionPane.showMessageDialog(null, "Kiralanan Arac Kayıt Eklendi", "Sonuç",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, "Kiralanan Arac Kayıt  Eklenemedi", "Sonuç", JOptionPane.PLAIN_MESSAGE);
		}

	}

	public void listelemeYapKiralananArac(String sqlKodu) throws SQLException {

		container = getContentPane();
		container.removeAll();
		yeniPanel = new JPanel(null);
		yeniPanel.setSize(1090, 590);
		yeniPanel.setBounds(0, 0, 1090, 590);
		yeniPanel.setBackground(Color.white);
		String[] tabloBasliklari = { "Kiralanan Arac ID", "Kiralayan Musteri ID" };

		resultKira = statement8.executeQuery(sqlKodu);
		resultKira.last();

		int stokSayisi = resultKira.getRow();
		resultKira.first();
		String[][] veri = new String[stokSayisi][9];
		for (int i = 0; i < stokSayisi; i++) {
			veri[i][0] = Integer.toString(resultKira.getInt("kiralananAracID"));
			veri[i][1] = Integer.toString(resultKira.getInt("kiralayanID"));

			resultKira.next();
		}

		tabloKira = new JTable(stokSayisi, 9);
		tabloKira.setBackground(Color.white);
		tabloKira.setSize(1050, 672);
		tabloKira.setBounds(0, 0, 1050, 672);
		// tablo.setFont(new Font("Serif", Font.BOLD, 20));
		tabloKira.setModel(new DefaultTableModel(veri, tabloBasliklari));
		JScrollPane panel = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		panel.setBounds(0, 0, 1095, 5672);
		panel.getViewport().add(tabloKira);
		// JTableHeader baslik = tabloKira.getTableHeader();
		// tablo.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		yeniPanel.add(panel);
		yeniPanel.setBackground(Color.lightGray);
		container.add(yeniPanel);
		invalidate();
		repaint();
	}

	public void teslimAl() {

		icerikArama("Kiradan Teslim Alinacak Arac ID Numarası");
		guncelleSec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					resultKira = statement.executeQuery("SELECT * FROM kiralananArac WHERE kiralananAracID = "
							+ Integer.parseInt(txtGuncelle.getText()));
					resultKira.first();
					resultKira.deleteRow();
					resultKira = statement.executeQuery("SELECT * FROM kiralananArac");

					JOptionPane.showMessageDialog(null, "Arac Kiradan  Teslim Alindi", "Sonuç",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException ex) {
					Logger.getLogger(arabaKirala.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		});
	}

	public void aracDurumIDOlustur() {

		aracDurumID = new JLabel("Arac Durum ID: ");
		aracDurumID.setSize(100, 30);
		aracDurumID.setBounds(10, 50, 200, 30);
		yeniPanel.add(aracDurumID);
		txtaracDurumID = new JTextField();
		txtaracDurumID.setSize(250, 20);
		txtaracDurumID.setBounds(265, 58, 250, 20);
		yeniPanel.add(txtaracDurumID);

	}

	public void durumBilgisiOlustur() {

		durum = new JLabel("Arac Durum Bilgisi: ");
		durum.setSize(100, 30);
		durum.setBounds(10, 80, 200, 30);
		yeniPanel.add(durum);
		txtdurum = new JTextField();
		txtdurum.setSize(250, 20);
		txtdurum.setBounds(265, 88, 250, 20);
		yeniPanel.add(txtdurum);

	}

	public void aracDurumMenusu() {

		container = getContentPane();
		container.removeAll();
		yeniPanel = new JPanel(null);
		yeniPanel.setSize(1100, 600);
		yeniPanel.setBounds(0, 0, 1100, 600);
		yeniPanel.setBackground(Color.white);

		aracDurumIDOlustur();
		durumBilgisiOlustur();

		JTextArea txtAnaSayfa = new JTextArea();
		txtAnaSayfa.setText("             ARAC DURUM BİLGİSİ");
		Font font = new Font("Default", Font.CENTER_BASELINE, 16);
		txtAnaSayfa.setFont(font);

		// Color color=new
		// Color(115,44,123);//http://www.colorcombos.com/colors/732C7B
		// purple

		txtAnaSayfa.setForeground(Color.BLACK);
		txtAnaSayfa.setSize(460, 40);
		txtAnaSayfa.setBounds(15, 15, 460, 30);

		Color color2 = UIManager.getColor("Panel.background");

		txtAnaSayfa.setBackground(color2);
		txtAnaSayfa.setEditable(false);

		yeniPanel.add(txtAnaSayfa);
		btnKaydet = new JButton("Kaydet");
		btnKaydet.setSize(100, 30);
		btnKaydet.setBounds(271, 530, 200, 30);

		btnKaydet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aracDurum();
			}

		});

		yeniPanel.add(btnKaydet);
		container.add(yeniPanel);
		invalidate();
		repaint();
	}

	public void aracDurum() {

		try {
			resultDurum.first();
			resultDurum.moveToInsertRow();

			resultDurum.updateInt("aracDurumID", Integer.parseInt(txtaracDurumID.getText()));
			resultDurum.updateString("durumBilgisi", txtdurum.getText());

			resultDurum.insertRow();

			resultDurum.beforeFirst();
			txtaracDurumID.setText("");
			txtdurum.setText("");

			JOptionPane.showMessageDialog(null, "Arac Durum Kayıt Eklendi", "Sonuç", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
		}

	}

	public void listelemeYapAracDurum(String sqlKodu) throws SQLException {

		container = getContentPane();
		container.removeAll();
		yeniPanel = new JPanel(null);
		yeniPanel.setSize(1090, 590);
		yeniPanel.setBounds(0, 0, 1090, 590);
		yeniPanel.setBackground(Color.white);
		String[] tabloBasliklari = { "Arac Durum ID", "Durum Bilgisi" };

		resultDurum = statement5.executeQuery(sqlKodu);
		resultDurum.last();

		int stokSayisi = resultDurum.getRow();
		resultDurum.first();
		String[][] veri = new String[stokSayisi][9];
		for (int i = 0; i < stokSayisi; i++) {
			veri[i][0] = Integer.toString(resultDurum.getInt("aracDurumID"));
			veri[i][1] = resultDurum.getString("durumBilgisi");

			resultDurum.next();
		}

		tabloDurum = new JTable(stokSayisi, 9);
		tabloDurum.setBackground(Color.white);
		tabloDurum.setSize(1050, 672);
		tabloDurum.setBounds(0, 0, 1050, 672);
		// tablo.setFont(new Font("Serif", Font.BOLD, 20));
		tabloDurum.setModel(new DefaultTableModel(veri, tabloBasliklari));
		JScrollPane panel = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		panel.setBounds(0, 0, 1095, 5672);
		panel.getViewport().add(tabloDurum);
		// JTableHeader baslik = tabloDurum.getTableHeader();
		// tablo.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		yeniPanel.add(panel);
		yeniPanel.setBackground(Color.lightGray);
		container.add(yeniPanel);
		invalidate();
		repaint();
	}

	public void aramaMenusuMusteri() {

		container = getContentPane();
		container.removeAll();
		yeniPanel = new JPanel(null);
		yeniPanel.setSize(1100, 590);
		yeniPanel.setBounds(0, 0, 1100, 590);
		yeniPanel.setBackground(Color.white);
		String[] secenekler = { "Musteri ID ye Gore Ara", "Telefon Numarasina Gore Ara", "Adrese Gore Ara",
				"Mail Adresine Gore Ara" };

		final JComboBox aramaSecenekleri = new JComboBox(secenekler);
		aramaSecenekleri.setSize(250, 25);
		aramaSecenekleri.setBounds(10, 50, 250, 25);
		yeniPanel.add(aramaSecenekleri);
		JButton sec = new JButton("Seç");
		sec.setSize(70, 25);
		sec.setBounds(270, 50, 70, 25);
		sec.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				switch (aramaSecenekleri.getSelectedIndex()) {
				case 0:
					icerikListelemeMusteri("Musteri ID giriniz: ", "musteriID");
					break;
				case 1:
					icerikListelemeMusteri("Musterinin Telefon Numarasini giriniz: ", "telefon");
					break;
				case 2:
					icerikListelemeMusteri("Musterinin Adresini giriniz: ", "adres");
					break;
				case 3:
					icerikListelemeMusteri("Musterinin Mail Adresini giriniz: ", "mail");
					break;
				}
			}
		});

		yeniPanel.add(sec);
		container.add(yeniPanel);
		invalidate();
		repaint();
	}

	public void icerikListelemeMusteri(String mesaj, final String sutun) {

		container = getContentPane();
		container.removeAll();
		icerikArama(mesaj);

		guncelleSec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (sutun.equals("musteriID"))
						listelemeYap("SELECT * FROM musteri WHERE " + sutun + "=" + txtGuncelle.getText());
					else
						listelemeYap(
								"SELECT * FROM musteri WHERE " + sutun + " LIKE" + "'" + txtGuncelle.getText() + "'");

				} catch (SQLException ex) {
					JOptionPane.showConfirmDialog(null, "Baglantı Basarısız Musteri Arama", "Postgresql Baglantısı",
							JOptionPane.PLAIN_MESSAGE);
				}
			}
		}

		);

		yeniPanel = new JPanel(null);
		yeniPanel.setSize(1090, 590);
		yeniPanel.setBounds(0, 0, 1090, 590);
		yeniPanel.setBackground(Color.white);
		container.add(yeniPanel);
		invalidate();
		repaint();

	}

	public void aramaMenusuKisi() {

		container = getContentPane();
		container.removeAll();
		yeniPanel = new JPanel(null);
		yeniPanel.setSize(1100, 590);
		yeniPanel.setBounds(0, 0, 1100, 590);
		yeniPanel.setBackground(Color.white);
		String[] secenekler = { "Kisi ID ye Gore Ara", "Kisinin Ismine Gore Ara", "Kisinin Soyismine Gore Ara",
				"Cinsiyete Gore Ara", "Yasa Gore Ara", "Kisinin TC Kimlik Numarasina Gore Ara", "Ehliyete Gore Ara" };

		final JComboBox aramaSecenekleri = new JComboBox(secenekler);
		aramaSecenekleri.setSize(250, 25);
		aramaSecenekleri.setBounds(10, 50, 250, 25);
		yeniPanel.add(aramaSecenekleri);
		JButton sec = new JButton("Seç");
		sec.setSize(70, 25);
		sec.setBounds(270, 50, 70, 25);
		sec.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				switch (aramaSecenekleri.getSelectedIndex()) {
				case 0:
					icerikListelemeKisi("Kisi ID giriniz: ", "kmusteriID");
					break;
				case 1:
					icerikListelemeKisi("Kisinin Ismini giriniz: ", "isim");
					break;
				case 2:
					icerikListelemeKisi("Kisinin Soyismini giriniz: ", "soyisim");
					break;
				case 3:
					icerikListelemeKisi("Cinsiyet giriniz: ", "cinsiyet");
					break;
				case 4:
					icerikListelemeKisi("Yas giriniz: ", "yas");
					break;
				case 5:
					icerikListelemeKisi("TC Kimlik Numarasi giriniz: ", "TC");
					break;
				case 6:
					icerikListelemeKisi("Ehliyet Sinifi giriniz: ", "ehliyetSinifi");
					break;
				}
			}
		});

		yeniPanel.add(sec);
		container.add(yeniPanel);
		invalidate();
		repaint();
	}

	public void icerikListelemeKisi(String mesaj, final String sutun) {

		container = getContentPane();
		container.removeAll();
		icerikArama(mesaj);

		guncelleSec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (sutun.equals("kmusteriID") | sutun.equals("yas"))
						listelemeYapKisi("SELECT * FROM kisi WHERE " + sutun + "=" + txtGuncelle.getText());
					else
						listelemeYapKisi(
								"SELECT * FROM kisi WHERE " + sutun + " LIKE" + "'" + txtGuncelle.getText() + "'");

				} catch (SQLException ex) {
					JOptionPane.showConfirmDialog(null, "Baglantı Basarısız Kisi Arama", "Postgresql Baglantısı",
							JOptionPane.PLAIN_MESSAGE);
				}
			}
		}

		);

		yeniPanel = new JPanel(null);
		yeniPanel.setSize(1090, 590);
		yeniPanel.setBounds(0, 0, 1090, 590);
		yeniPanel.setBackground(Color.white);
		container.add(yeniPanel);
		invalidate();
		repaint();

	}

	public void aramaMenusuSirket() {

		container = getContentPane();
		container.removeAll();
		yeniPanel = new JPanel(null);
		yeniPanel.setSize(1100, 590);
		yeniPanel.setBounds(0, 0, 1100, 590);
		yeniPanel.setBackground(Color.white);
		String[] secenekler = { "Sirket ID ye Gore Ara", "Sirketin Ismine Gore Ara" };

		final JComboBox aramaSecenekleri = new JComboBox(secenekler);
		aramaSecenekleri.setSize(250, 25);
		aramaSecenekleri.setBounds(10, 50, 250, 25);
		yeniPanel.add(aramaSecenekleri);
		JButton sec = new JButton("Seç");
		sec.setSize(70, 25);
		sec.setBounds(270, 50, 70, 25);
		sec.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				switch (aramaSecenekleri.getSelectedIndex()) {
				case 0:
					icerikListelemeSirket("Sirket ID giriniz: ", "smusteriID");
					break;
				case 1:
					icerikListelemeSirket("Sirketin Ismini giriniz: ", "sirketIsmi");
					break;

				}
			}
		});

		yeniPanel.add(sec);
		container.add(yeniPanel);
		invalidate();
		repaint();
	}

	public void icerikListelemeSirket(String mesaj, final String sutun) {

		container = getContentPane();
		container.removeAll();
		icerikArama(mesaj);

		guncelleSec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (sutun.equals("smusteriID"))
						listelemeYapSirket("SELECT * FROM sirket WHERE " + sutun + "=" + txtGuncelle.getText());
					else
						listelemeYapSirket(
								"SELECT * FROM sirket WHERE " + sutun + " LIKE" + "'" + txtGuncelle.getText() + "'");

				} catch (SQLException ex) {
					JOptionPane.showConfirmDialog(null, "Baglantı Basarısız Sirket Arama", "Postgresql Baglantısı",
							JOptionPane.PLAIN_MESSAGE);
				}
			}
		}

		);

		yeniPanel = new JPanel(null);
		yeniPanel.setSize(1090, 590);
		yeniPanel.setBounds(0, 0, 1090, 590);
		yeniPanel.setBackground(Color.white);
		container.add(yeniPanel);
		invalidate();
		repaint();

	}

	public void aramaMenusuArac() {

		container = getContentPane();
		container.removeAll();
		yeniPanel = new JPanel(null);
		yeniPanel.setSize(1100, 590);
		yeniPanel.setBounds(0, 0, 1100, 590);
		yeniPanel.setBackground(Color.white);
		String[] secenekler = { "Arac ID ye Gore Ara", "Aracin Durum Bilgisine Gore Ara", "Plaka Numarasina Gore Ara",
				"Vites Turune Gore Ara", "Arac Markasina Gore Ara", "Arac Modeline Gore Ara", "Arac Fiyatina Gore Ara",
				"Kasko Bilgisine Gore Ara", "Km Durumuna Gore Ara", "Renke Gore Ara", "Yakit Turune Gore Ara",
				"Motor Gucune Gore Ara" };

		final JComboBox aramaSecenekleri = new JComboBox(secenekler);
		aramaSecenekleri.setSize(250, 25);
		aramaSecenekleri.setBounds(10, 50, 250, 25);
		yeniPanel.add(aramaSecenekleri);
		JButton sec = new JButton("Seç");
		sec.setSize(70, 25);
		sec.setBounds(270, 50, 70, 25);
		sec.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				switch (aramaSecenekleri.getSelectedIndex()) {
				case 0:
					icerikListelemeArac("Arac ID giriniz: ", "aracID");
					break;
				case 1:
					icerikListelemeArac("Durum Bilgisi giriniz: ", "durumID");
					break;
				case 2:
					icerikListelemeArac("Aracin Plakasini giriniz: ", "plakaNo");
					break;
				case 3:
					icerikListelemeArac("Vites Turunu giriniz: ", "vites");
					break;
				case 4:
					icerikListelemeArac("Aracin Markasini giriniz: ", "marka");
					break;
				case 5:
					icerikListelemeArac("Aracin Modelini giriniz: ", "model");
					break;
				case 6:
					icerikListelemeArac("Fiyat giriniz: ", "fiyat");
					break;
				case 7:
					icerikListelemeArac("Kasko Bilgisi Giriniz giriniz: ", "kaskoBilgisi");
					break;
				case 8:
					icerikListelemeArac("Km Bilgisi Giriniz giriniz: ", "km");
					break;
				case 9:
					icerikListelemeArac("Renk giriniz: ", "renk");
					break;
				case 10:
					icerikListelemeArac("Yakit Turu giriniz: ", "yakitTuru");
					break;
				case 11:
					icerikListelemeArac("Motor Gucu giriniz: ", "motorGucu");
					break;

				}
			}
		});

		yeniPanel.add(sec);
		container.add(yeniPanel);
		invalidate();
		repaint();
	}

	public void icerikListelemeArac(String mesaj, final String sutun) {

		container = getContentPane();
		container.removeAll();
		icerikArama(mesaj);

		guncelleSec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (sutun.equals("aracID") | sutun.equals("durumID"))
						listelemeYapArac("SELECT * FROM arac WHERE " + sutun + "=" + txtGuncelle.getText());
					else
						listelemeYapArac(
								"SELECT * FROM arac WHERE " + sutun + " LIKE" + "'" + txtGuncelle.getText() + "'");

				} catch (SQLException ex) {
					JOptionPane.showConfirmDialog(null, "Baglantı Basarısız Arac Arama", "Postgresql Baglantısı",
							JOptionPane.PLAIN_MESSAGE);
				}
			}
		}

		);

		yeniPanel = new JPanel(null);
		yeniPanel.setSize(1090, 590);
		yeniPanel.setBounds(0, 0, 1090, 590);
		yeniPanel.setBackground(Color.white);
		container.add(yeniPanel);
		invalidate();
		repaint();

	}

	public void aramaMenusuAraba() {

		container = getContentPane();
		container.removeAll();
		yeniPanel = new JPanel(null);
		yeniPanel.setSize(1100, 590);
		yeniPanel.setBounds(0, 0, 1100, 590);
		yeniPanel.setBackground(Color.white);
		String[] secenekler = { "Araba ID ye Gore Ara", "Kapi Sayisina Gore Ara" };

		final JComboBox aramaSecenekleri = new JComboBox(secenekler);
		aramaSecenekleri.setSize(250, 25);
		aramaSecenekleri.setBounds(10, 50, 250, 25);
		yeniPanel.add(aramaSecenekleri);
		JButton sec = new JButton("Seç");
		sec.setSize(70, 25);
		sec.setBounds(270, 50, 70, 25);
		sec.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				switch (aramaSecenekleri.getSelectedIndex()) {
				case 0:
					icerikListelemeAraba("Araba ID giriniz: ", "arabaID");
					break;
				case 1:
					icerikListelemeAraba("Kapi Sayisi giriniz: ", "kapiSayisi");
					break;

				}
			}
		});

		yeniPanel.add(sec);
		container.add(yeniPanel);
		invalidate();
		repaint();
	}

	public void icerikListelemeAraba(String mesaj, final String sutun) {

		container = getContentPane();
		container.removeAll();
		icerikArama(mesaj);

		guncelleSec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (sutun.equals("arabaID"))
						listelemeYapAraba("SELECT * FROM araba WHERE " + sutun + "=" + txtGuncelle.getText());
					else
						listelemeYapAraba(
								"SELECT * FROM araba WHERE " + sutun + " LIKE" + "'" + txtGuncelle.getText() + "'");

				} catch (SQLException ex) {
					JOptionPane.showConfirmDialog(null, "Baglantı Basarısız Araba Arama", "Postgresql Baglantısı",
							JOptionPane.PLAIN_MESSAGE);
				}
			}
		}

		);

		yeniPanel = new JPanel(null);
		yeniPanel.setSize(1090, 590);
		yeniPanel.setBounds(0, 0, 1090, 590);
		yeniPanel.setBackground(Color.white);
		container.add(yeniPanel);
		invalidate();
		repaint();

	}

	public void aramaMenusuIsMakinesi() {

		container = getContentPane();
		container.removeAll();
		yeniPanel = new JPanel(null);
		yeniPanel.setSize(1100, 590);
		yeniPanel.setBounds(0, 0, 1100, 590);
		yeniPanel.setBackground(Color.white);
		String[] secenekler = { "Is Makinesi ID ye Gore Ara", "Tonaja Gore Ara" };

		final JComboBox aramaSecenekleri = new JComboBox(secenekler);
		aramaSecenekleri.setSize(250, 25);
		aramaSecenekleri.setBounds(10, 50, 250, 25);
		yeniPanel.add(aramaSecenekleri);
		JButton sec = new JButton("Seç");
		sec.setSize(70, 25);
		sec.setBounds(270, 50, 70, 25);
		sec.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				switch (aramaSecenekleri.getSelectedIndex()) {
				case 0:
					icerikListelemeIsMakinesi("Is Makinesi ID giriniz: ", "ismakinesiID");
					break;
				case 1:
					icerikListelemeIsMakinesi("Tonaj giriniz: ", "tonaj");
					break;

				}
			}
		});

		yeniPanel.add(sec);
		container.add(yeniPanel);
		invalidate();
		repaint();
	}

	public void icerikListelemeIsMakinesi(String mesaj, final String sutun) {

		container = getContentPane();
		container.removeAll();
		icerikArama(mesaj);

		guncelleSec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (sutun.equals("ismakinesiID"))
						listelemeYapIsMakinesi("SELECT * FROM ismakinesi WHERE " + sutun + "=" + txtGuncelle.getText());
					else
						listelemeYapIsMakinesi("SELECT * FROM ismakinesi WHERE " + sutun + " LIKE" + "'"
								+ txtGuncelle.getText() + "'");

				} catch (SQLException ex) {
					JOptionPane.showConfirmDialog(null, "Baglantı Basarısız Is Makinesi Arama", "Postgresql Baglantısı",
							JOptionPane.PLAIN_MESSAGE);
				}
			}
		}

		);

		yeniPanel = new JPanel(null);
		yeniPanel.setSize(1090, 590);
		yeniPanel.setBounds(0, 0, 1090, 590);
		yeniPanel.setBackground(Color.white);
		container.add(yeniPanel);
		invalidate();
		repaint();

	}

	public void aramaMenusuDurum() {

		container = getContentPane();
		container.removeAll();
		yeniPanel = new JPanel(null);
		yeniPanel.setSize(1100, 590);
		yeniPanel.setBounds(0, 0, 1100, 590);
		yeniPanel.setBackground(Color.white);
		String[] secenekler = { "Arac Durum ID ye Gore Ara", "Durum Bilgisine Gore Ara" };

		final JComboBox aramaSecenekleri = new JComboBox(secenekler);
		aramaSecenekleri.setSize(250, 25);
		aramaSecenekleri.setBounds(10, 50, 250, 25);
		yeniPanel.add(aramaSecenekleri);
		JButton sec = new JButton("Seç");
		sec.setSize(70, 25);
		sec.setBounds(270, 50, 70, 25);
		sec.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				switch (aramaSecenekleri.getSelectedIndex()) {
				case 0:
					icerikListelemeDurum("Arac Durum ID giriniz: ", "aracDurumID");
					break;
				case 1:
					icerikListelemeDurum("Durum Bilgisi giriniz: ", "durumBilgisi");
					break;

				}
			}
		});

		yeniPanel.add(sec);
		container.add(yeniPanel);
		invalidate();
		repaint();
	}

	public void icerikListelemeDurum(String mesaj, final String sutun) {

		container = getContentPane();
		container.removeAll();
		icerikArama(mesaj);

		guncelleSec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (sutun.equals("aracDurumID"))
						listelemeYapAracDurum("SELECT * FROM durum WHERE " + sutun + "=" + txtGuncelle.getText());
					else
						listelemeYapAracDurum(
								"SELECT * FROM durum WHERE " + sutun + " LIKE" + "'" + txtGuncelle.getText() + "'");

				} catch (SQLException ex) {
					JOptionPane.showConfirmDialog(null, "Baglantı Basarısız Durum Arama", "Postgresql Baglantısı",
							JOptionPane.PLAIN_MESSAGE);
				}
			}
		}

		);

		yeniPanel = new JPanel(null);
		yeniPanel.setSize(1090, 590);
		yeniPanel.setBounds(0, 0, 1090, 590);
		yeniPanel.setBackground(Color.white);
		container.add(yeniPanel);
		invalidate();
		repaint();

	}

	public void aramaMenusuKira() {

		container = getContentPane();
		container.removeAll();
		yeniPanel = new JPanel(null);
		yeniPanel.setSize(1100, 590);
		yeniPanel.setBounds(0, 0, 1100, 590);
		yeniPanel.setBackground(Color.white);
		String[] secenekler = { "Kirada Olan Arac ID ye Gore Ara", "Arac Kiralayan Musteri ID ye Gore Ara" };

		final JComboBox aramaSecenekleri = new JComboBox(secenekler);
		aramaSecenekleri.setSize(250, 25);
		aramaSecenekleri.setBounds(10, 50, 270, 25);
		yeniPanel.add(aramaSecenekleri);
		JButton sec = new JButton("Seç");
		sec.setSize(70, 25);
		sec.setBounds(290, 50, 70, 25);
		sec.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				switch (aramaSecenekleri.getSelectedIndex()) {
				case 0:
					icerikListelemeKira("Kirada Olan Arac ID giriniz: ", "kiralananAracID");
					break;
				case 1:
					icerikListelemeKira("Arac Kiralayan Musteri ID giriniz: ", "kiralayanID");
					break;

				}
			}
		});

		yeniPanel.add(sec);
		container.add(yeniPanel);
		invalidate();
		repaint();
	}

	public void icerikListelemeKira(String mesaj, final String sutun) {

		container = getContentPane();
		container.removeAll();
		icerikArama(mesaj);

		guncelleSec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					listelemeYapKiralananArac(
							"SELECT * FROM kiralananArac WHERE " + sutun + "=" + txtGuncelle.getText());
				} catch (SQLException ex) {
					JOptionPane.showConfirmDialog(null, "Baglantı Basarısız Kiralanan Arac Arama",
							"Postgresql Baglantısı", JOptionPane.PLAIN_MESSAGE);
				}
			}
		}

		);

		yeniPanel = new JPanel(null);
		yeniPanel.setSize(1090, 590);
		yeniPanel.setBounds(0, 0, 1090, 590);
		yeniPanel.setBackground(Color.white);
		container.add(yeniPanel);
		invalidate();
		repaint();

	}

	public void islemNumarasiOlustur() {

		islemNumarasi = new JLabel("Islem ID: ");
		islemNumarasi.setSize(100, 30);
		islemNumarasi.setBounds(10, 50, 150, 30);
		yeniPanel.add(islemNumarasi);
		txtislemNumarasi = new JTextField();
		txtislemNumarasi.setSize(250, 20);
		txtislemNumarasi.setBounds(125, 58, 250, 20);
		// txtmusteriNumarasi.setEditable(false);
		yeniPanel.add(txtislemNumarasi);
	}

	public void islemTutariOlustur() {

		islemTutari = new JLabel("Islem Tutari: ");
		islemTutari.setSize(100, 30);
		islemTutari.setBounds(10, 80, 150, 30);
		yeniPanel.add(islemTutari);
		txtislemTutari = new JTextField();
		txtislemTutari.setSize(250, 20);
		txtislemTutari.setBounds(125, 88, 250, 20);
		yeniPanel.add(txtislemTutari);

	}

	public void yeniKayitMenusuIslem() {

		container = getContentPane();
		container.removeAll();
		yeniPanel = new JPanel(null);
		yeniPanel.setSize(1100, 600);
		yeniPanel.setBounds(0, 0, 1100, 600);
		yeniPanel.setBackground(Color.white);

		islemNumarasiOlustur();
		islemTutariOlustur();

		JTextArea txtAnaSayfa = new JTextArea();
		txtAnaSayfa.setText("             ISLEM BİLGİLERİ");
		Font font = new Font("Default", Font.CENTER_BASELINE, 16);
		txtAnaSayfa.setFont(font);

		// Color color=new
		// Color(115,44,123);//http://www.colorcombos.com/colors/732C7B
		// purple

		txtAnaSayfa.setForeground(Color.BLACK);
		txtAnaSayfa.setSize(460, 40);
		txtAnaSayfa.setBounds(15, 15, 460, 30);

		Color color2 = UIManager.getColor("Panel.background");

		txtAnaSayfa.setBackground(color2);
		txtAnaSayfa.setEditable(false);

		yeniPanel.add(txtAnaSayfa);
		btnKaydet = new JButton("Kaydet");
		btnKaydet.setSize(100, 30);
		btnKaydet.setBounds(271, 530, 200, 30);

		btnKaydet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IslemYeniKayit();
			}

		});

		yeniPanel.add(btnKaydet);
		container.add(yeniPanel);
		invalidate();
		repaint();
	}

	public void IslemYeniKayit() {
		try {
			resultIslem.first();
			resultIslem.moveToInsertRow();

			resultIslem.updateInt("islemID", Integer.parseInt(txtislemNumarasi.getText()));
			resultIslem.updateString("islemTutari", (txtislemTutari.getText()));

			resultIslem.insertRow();

			resultIslem.beforeFirst();

			txtislemNumarasi.setText("");
			txtislemTutari.setText("");

			JOptionPane.showMessageDialog(null, "Islem Kayıt Eklendi", "Sonuç", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, "Islem Kayıt Eklenemedi", "Sonuç", JOptionPane.PLAIN_MESSAGE);
		}
	}

	public void listelemeYapIslem(String sqlKodu) throws SQLException {

		container = getContentPane();
		container.removeAll();
		yeniPanel = new JPanel(null);
		yeniPanel.setSize(1090, 590);
		yeniPanel.setBounds(0, 0, 1090, 590);
		yeniPanel.setBackground(Color.white);
		String[] tabloBasliklari = { "islemID", "islemTutari" };

		resultIslem = statement9.executeQuery(sqlKodu);

		resultIslem.last();

		int stokSayisi = resultIslem.getRow();
		resultIslem.first();
		String[][] veri = new String[stokSayisi][9];
		for (int i = 0; i < stokSayisi; i++) {
			veri[i][0] = Integer.toString(resultIslem.getInt("islemID"));
			veri[i][1] = resultIslem.getString("islemTutari");

			resultIslem.next();
		}

		tabloIslem = new JTable(stokSayisi, 9);
		tabloIslem.setBackground(Color.white);
		tabloIslem.setSize(1050, 672);
		tabloIslem.setBounds(0, 0, 1050, 672);
		// tablo.setFont(new Font("Serif", Font.BOLD, 20));
		tabloIslem.setModel(new DefaultTableModel(veri, tabloBasliklari));
		JScrollPane panel = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		panel.setBounds(0, 0, 1095, 5672);
		panel.getViewport().add(tabloIslem);
		// JTableHeader baslik = tabloAraba.getTableHeader();
		// tablo.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		yeniPanel.add(panel);
		yeniPanel.setBackground(Color.lightGray);
		container.add(yeniPanel);
		invalidate();
		repaint();
	}

	public void guncellemeMenusuIslem() {

		icerikArama("Güncellenecek Islem ID");
		guncelleSec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					container = getContentPane();
					container.removeAll();
					yeniPanel = new JPanel(null);
					yeniPanel.setSize(1100, 590);
					yeniPanel.setBounds(0, 0, 1100, 590);
					yeniPanel.setBackground(Color.white);
					resultIslem = statement.executeQuery(
							"SELECT * FROM islem WHERE islemID =  " + Integer.parseInt(txtGuncelle.getText()));
					resultIslem.first();

					islemNumarasiOlustur();
					txtislemNumarasi.setEditable(false);
					txtislemNumarasi.setText(txtGuncelle.getText());

					islemTutariOlustur();
					txtislemTutari.setText(resultIslem.getString("islemTutari"));

					JButton btnGuncelle = new JButton("Güncelle");
					btnGuncelle.setSize(70, 30);
					btnGuncelle.setBounds(271, 330, 100, 30);
					yeniPanel.add(btnGuncelle);
					btnGuncelle.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try {
								resultIslem.updateString("islemTutari", (txtislemTutari.getText()));

								resultIslem.updateRow();
								resultIslem = statement9.executeQuery("SELECT * FROM islem");
								JOptionPane.showMessageDialog(null, "Kayıt Güncellendi", "Sonuç",
										JOptionPane.INFORMATION_MESSAGE);
							} catch (SQLException ex) {
								JOptionPane.showConfirmDialog(null, "Güncelleme sırasında hata olustu.", "Sonuc",
										JOptionPane.PLAIN_MESSAGE);
							}
						}
					});
					container.add(yeniPanel);
					invalidate();
					repaint();
				} catch (SQLException ex) {
					Logger.getLogger(arabaKirala.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		});

	}

	public void aramaMenusuIslem() {

		container = getContentPane();
		container.removeAll();
		yeniPanel = new JPanel(null);
		yeniPanel.setSize(1100, 590);
		yeniPanel.setBounds(0, 0, 1100, 590);
		yeniPanel.setBackground(Color.white);
		String[] secenekler = { "Islem ID ye Gore Ara", "Islem Tutarina Gore Ara" };

		final JComboBox aramaSecenekleri = new JComboBox(secenekler);
		aramaSecenekleri.setSize(250, 25);
		aramaSecenekleri.setBounds(10, 50, 250, 25);
		yeniPanel.add(aramaSecenekleri);
		JButton sec = new JButton("Seç");
		sec.setSize(70, 25);
		sec.setBounds(270, 50, 70, 25);
		sec.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				switch (aramaSecenekleri.getSelectedIndex()) {
				case 0:
					icerikListelemeIslem("Islem ID giriniz: ", "islemID");
					break;
				case 1:
					icerikListelemeIslem("Islem Tutari giriniz: ", "islemTutari");
					break;

				}
			}
		});

		yeniPanel.add(sec);
		container.add(yeniPanel);
		invalidate();
		repaint();
	}

	public void icerikListelemeIslem(String mesaj, final String sutun) {

		container = getContentPane();
		container.removeAll();
		icerikArama(mesaj);

		guncelleSec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (sutun.equals("islemID"))
						listelemeYapIslem("SELECT * FROM islem WHERE " + sutun + "=" + txtGuncelle.getText());
					else
						listelemeYapIslem(
								"SELECT * FROM islem WHERE " + sutun + " LIKE" + "'" + txtGuncelle.getText() + "'");

				} catch (SQLException ex) {
					JOptionPane.showConfirmDialog(null, "Baglantı Basarısız Islem Arama", "Postgresql Baglantısı",
							JOptionPane.PLAIN_MESSAGE);
				}
			}
		}

		);

		yeniPanel = new JPanel(null);
		yeniPanel.setSize(1090, 590);
		yeniPanel.setBounds(0, 0, 1090, 590);
		yeniPanel.setBackground(Color.white);
		container.add(yeniPanel);
		invalidate();
		repaint();

	}

	public void TeslimatislemIDOlustur() {

		teslimatNumarasi = new JLabel("Teslimat ID: ");
		teslimatNumarasi.setSize(100, 30);
		teslimatNumarasi.setBounds(10, 50, 190, 30);
		yeniPanel.add(teslimatNumarasi);
		txtteslimatNumarasi = new JTextField();
		txtteslimatNumarasi.setSize(250, 20);
		txtteslimatNumarasi.setBounds(195, 58, 250, 20);
		// txtmusteriNumarasi.setEditable(false);
		yeniPanel.add(txtteslimatNumarasi);
	}

	public void teslimatSaatiOlustur() {

		teslimatSaat = new JLabel("Teslimat Saati: ");
		teslimatSaat.setSize(100, 30);
		teslimatSaat.setBounds(10, 80, 190, 30);
		yeniPanel.add(teslimatSaat);
		txtteslimatSaat = new JTextField();
		txtteslimatSaat.setSize(250, 20);
		txtteslimatSaat.setBounds(195, 88, 250, 20);
		yeniPanel.add(txtteslimatSaat);

	}

	public void teslimatTarihiOlustur() {

		teslimatTarih = new JLabel("Teslimat Tarihi: ");
		teslimatTarih.setSize(100, 30);
		teslimatTarih.setBounds(10, 110, 190, 30);
		yeniPanel.add(teslimatTarih);
		txtteslimatTarih = new JTextField();
		txtteslimatTarih.setSize(250, 20);
		txtteslimatTarih.setBounds(195, 118, 250, 20);
		yeniPanel.add(txtteslimatTarih);

	}

	public void tAracIDOlustur() {

		teslimArac = new JLabel("Teslim Alinan Arac ID:");
		teslimArac.setSize(100, 30);
		teslimArac.setBounds(10, 140, 190, 30);
		yeniPanel.add(teslimArac);
		txtteslimArac = new JTextField();
		txtteslimArac.setSize(250, 20);
		txtteslimArac.setBounds(195, 148, 250, 20);
		yeniPanel.add(txtteslimArac);

	}

	public void tMusteriIDOlustur() {

		teslimMusteri = new JLabel("Teslim Veren Musteri ID:");
		teslimMusteri.setSize(100, 30);
		teslimMusteri.setBounds(10, 170, 190, 30);
		yeniPanel.add(teslimMusteri);
		txtteslimMusteri = new JTextField();
		txtteslimMusteri.setSize(250, 20);
		txtteslimMusteri.setBounds(195, 178, 250, 20);
		yeniPanel.add(txtteslimMusteri);

	}

	public void yeniKayitMenusuTeslimat() {

		container = getContentPane();
		container.removeAll();
		yeniPanel = new JPanel(null);
		yeniPanel.setSize(1100, 600);
		yeniPanel.setBounds(0, 0, 1100, 600);

		yeniPanel.setBackground(Color.white);

		TeslimatislemIDOlustur();
		teslimatSaatiOlustur();
		teslimatTarihiOlustur();
		tAracIDOlustur();
		tMusteriIDOlustur();

		JTextArea txtAnaSayfa = new JTextArea();
		txtAnaSayfa.setText("             TESLIMAT BILGILERI");
		Font font = new Font("Default", Font.CENTER_BASELINE, 16);
		txtAnaSayfa.setFont(font);

		// Color color=new
		// Color(115,44,123);//http://www.colorcombos.com/colors/732C7B
		// purple

		txtAnaSayfa.setForeground(Color.BLACK);
		txtAnaSayfa.setSize(460, 40);
		txtAnaSayfa.setBounds(15, 15, 460, 30);

		Color color2 = UIManager.getColor("Panel.background");

		txtAnaSayfa.setBackground(color2);
		txtAnaSayfa.setEditable(false);

		yeniPanel.add(txtAnaSayfa);
		btnKaydet = new JButton(" Kaydet");
		btnKaydet.setSize(100, 30);
		btnKaydet.setBounds(271, 530, 200, 30);

		btnKaydet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teslimatYeniKayit();
			}

		});

		yeniPanel.add(btnKaydet);
		container.add(yeniPanel);
		invalidate();
		repaint();
	}

	public void teslimatYeniKayit() {

		try {
			resultTeslimat.first();
			resultTeslimat.moveToInsertRow();

			resultTeslimat.updateInt("TeslimatislemID", Integer.parseInt(txtteslimatNumarasi.getText()));
			resultTeslimat.updateString("teslimatSaati", txtteslimatSaat.getText());
			resultTeslimat.updateString("teslimatTarihi", txtteslimatTarih.getText());
			resultTeslimat.updateInt("tAracID", Integer.parseInt(txtteslimArac.getText()));
			resultTeslimat.updateInt("tMusteriID", Integer.parseInt(txtteslimMusteri.getText()));

			resultTeslimat.insertRow();

			resultTeslimat.beforeFirst();

			txtteslimatNumarasi.setText("");
			txtteslimatSaat.setText("");
			txtteslimatTarih.setText("");
			txtteslimArac.setText("");
			txtteslimMusteri.setText("");
			JOptionPane.showMessageDialog(null, "Teslimat Kayıt Eklendi", "Sonuç", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, "Teslimat Kayıt Eklenemedi", "Sonuç", JOptionPane.PLAIN_MESSAGE);
		}

	}

	public void listelemeYapTeslimat(String sqlKodu) throws SQLException {

		container = getContentPane();
		container.removeAll();
		yeniPanel = new JPanel(null);
		yeniPanel.setSize(1090, 590);
		yeniPanel.setBounds(0, 0, 1090, 590);
		yeniPanel.setBackground(Color.white);
		String[] tabloBasliklari = { "Teslimat islem ID", "Teslimat Saati", "Teslimat Tarihi", "Teslim Alinan Arac ID",
				"Teslim Veren Musteri ID" };

		resultTeslimat = statement10.executeQuery(sqlKodu);

		resultTeslimat.last();

		int stokSayisi = resultTeslimat.getRow();
		resultTeslimat.first();
		String[][] veri = new String[stokSayisi][9];
		for (int i = 0; i < stokSayisi; i++) {
			veri[i][0] = Integer.toString(resultTeslimat.getInt("TeslimatislemID"));
			veri[i][1] = resultTeslimat.getString("teslimatSaati");
			veri[i][2] = resultTeslimat.getString("teslimatTarihi");
			veri[i][3] = Integer.toString(resultTeslimat.getInt("tAracID"));
			veri[i][4] = Integer.toString(resultTeslimat.getInt("tMusteriID"));
			resultTeslimat.next();
		}

		tabloTeslimat = new JTable(stokSayisi, 9);
		tabloTeslimat.setBackground(Color.white);
		tabloTeslimat.setSize(1050, 672);
		tabloTeslimat.setBounds(0, 0, 1050, 672);
		// tablo.setFont(new Font("Serif", Font.BOLD, 20));
		tabloTeslimat.setModel(new DefaultTableModel(veri, tabloBasliklari));
		JScrollPane panel = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		panel.setBounds(0, 0, 1095, 5672);
		panel.getViewport().add(tabloTeslimat);
		// JTableHeader baslik = tablo.getTableHeader();
		// tablo.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		yeniPanel.add(panel);
		yeniPanel.setBackground(Color.lightGray);
		container.add(yeniPanel);
		invalidate();
		repaint();
	}

	public void guncellemeMenusuTeslimat() {

		icerikArama("Güncellenecek Teslimat ID");
		guncelleSec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					container = getContentPane();
					container.removeAll();
					yeniPanel = new JPanel(null);
					yeniPanel.setSize(1100, 590);
					yeniPanel.setBounds(0, 0, 1100, 590);
					yeniPanel.setBackground(Color.white);
					resultTeslimat = statement10.executeQuery("SELECT * FROM teslimat WHERE TeslimatislemID =  "
							+ Integer.parseInt(txtGuncelle.getText()));
					resultTeslimat.first();

					TeslimatislemIDOlustur();
					txtteslimatNumarasi.setEditable(false);
					txtteslimatNumarasi.setText(txtGuncelle.getText());

					teslimatSaatiOlustur();
					txtteslimatSaat.setText(resultTeslimat.getString("teslimatSaati"));
					teslimatTarihiOlustur();
					txtteslimatTarih.setText(resultTeslimat.getString("teslimatTarihi"));
					tAracIDOlustur();
					txtteslimArac.setText(resultTeslimat.getString("tAracID"));
					tMusteriIDOlustur();
					txtteslimMusteri.setText(resultTeslimat.getString("tMusteriID"));

					JButton btnGuncelle = new JButton("Güncelle");
					btnGuncelle.setSize(70, 30);
					btnGuncelle.setBounds(271, 330, 100, 30);
					yeniPanel.add(btnGuncelle);
					btnGuncelle.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try {
								resultTeslimat.updateString("teslimatSaati", (txtteslimatSaat.getText()));
								resultTeslimat.updateString("teslimatTarihi", (txtteslimatTarih.getText()));
								resultTeslimat.updateInt("tAracID", Integer.parseInt(txtteslimArac.getText()));
								resultTeslimat.updateInt("tMusteriID", Integer.parseInt(txtteslimMusteri.getText()));

								resultTeslimat.updateRow();
								resultTeslimat = statement10.executeQuery("SELECT * FROM teslimat");
								JOptionPane.showMessageDialog(null, "Kayıt Güncellendi", "Sonuç",
										JOptionPane.INFORMATION_MESSAGE);
							} catch (SQLException ex) {
								JOptionPane.showConfirmDialog(null, "Güncelleme sırasında hata olustu.", "Sonuc",
										JOptionPane.PLAIN_MESSAGE);
							}
						}
					});
					container.add(yeniPanel);
					invalidate();
					repaint();
				} catch (SQLException ex) {
					Logger.getLogger(arabaKirala.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		});

	}

	public void teslimatSil() {

		icerikArama("Silinecek Teslimatin ID Numarası");
		guncelleSec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					resultTeslimat = statement.executeQuery("SELECT * FROM teslimat WHERE TeslimatislemID = "
							+ Integer.parseInt(txtGuncelle.getText()));
					resultTeslimat.first();
					resultTeslimat.deleteRow();
					resultTeslimat = statement10.executeQuery("SELECT * FROM teslimat");

					resultIslem = statement9.executeQuery(
							"SELECT * FROM islem WHERE islemID = " + Integer.parseInt(txtGuncelle.getText()));
					resultIslem.first();
					resultIslem.deleteRow();
					resultIslem = statement.executeQuery("SELECT * FROM islem");

					JOptionPane.showMessageDialog(null, "Kayıt Silindi", "Sonuç", JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException ex) {
					Logger.getLogger(arabaKirala.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		});

	}

	public void aramaMenusuTeslimat() {

		container = getContentPane();
		container.removeAll();
		yeniPanel = new JPanel(null);
		yeniPanel.setSize(1100, 590);
		yeniPanel.setBounds(0, 0, 1100, 590);
		yeniPanel.setBackground(Color.white);
		String[] secenekler = { "Teslimat ID ye Gore Ara", "Teslimat Saatine Gore Ara", "Teslimat Tarihine Gore Ara",
				"Teslim Alinan Arac ID ye Gore Ara", "Teslim Veren Musteri ID ye Gore Ara" };

		final JComboBox aramaSecenekleri = new JComboBox(secenekler);
		aramaSecenekleri.setSize(250, 25);
		aramaSecenekleri.setBounds(10, 50, 250, 25);
		yeniPanel.add(aramaSecenekleri);
		JButton sec = new JButton("Seç");
		sec.setSize(70, 25);
		sec.setBounds(270, 50, 70, 25);
		sec.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				switch (aramaSecenekleri.getSelectedIndex()) {
				case 0:
					icerikListelemeTeslimat("Teslimat ID giriniz: ", "TeslimatislemID");
					break;
				case 1:
					icerikListelemeTeslimat("Teslimat Saatini giriniz: ", "teslimatSaati");
					break;
				case 2:
					icerikListelemeTeslimat("Teslimat Tarihi giriniz: ", "teslimatTarihi");
					break;
				case 3:
					icerikListelemeTeslimat("Teslim Alinan Arac ID giriniz: ", "tAracID");
					break;
				case 4:
					icerikListelemeTeslimat("Teslim Veren Musteri ID giriniz: ", "tMusteriID");
					break;

				}
			}
		});

		yeniPanel.add(sec);
		container.add(yeniPanel);
		invalidate();
		repaint();
	}

	public void icerikListelemeTeslimat(String mesaj, final String sutun) {

		container = getContentPane();
		container.removeAll();
		icerikArama(mesaj);

		guncelleSec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (sutun.equals("TeslimatislemID") | sutun.equals("tAracID") | sutun.equals("tMusteriID"))
						listelemeYapTeslimat("SELECT * FROM teslimat WHERE " + sutun + "=" + txtGuncelle.getText());
					else
						listelemeYapTeslimat(
								"SELECT * FROM teslimat WHERE " + sutun + " LIKE" + "'" + txtGuncelle.getText() + "'");

				} catch (SQLException ex) {
					JOptionPane.showConfirmDialog(null, "Baglantı Basarısız Teslimat Arama", "Postgresql Baglantısı",
							JOptionPane.PLAIN_MESSAGE);
				}
			}
		}

		);

		yeniPanel = new JPanel(null);
		yeniPanel.setSize(1090, 590);
		yeniPanel.setBounds(0, 0, 1090, 590);
		yeniPanel.setBackground(Color.white);
		container.add(yeniPanel);
		invalidate();
		repaint();

	}

	public void SozlesmeislemIDOlustur() {

		sozlesmeNumarasi = new JLabel("Sozlesme ID: ");
		sozlesmeNumarasi.setSize(100, 30);
		sozlesmeNumarasi.setBounds(10, 50, 220, 30);
		yeniPanel.add(sozlesmeNumarasi);
		txtsozlesmeNumarasi = new JTextField();
		txtsozlesmeNumarasi.setSize(250, 20);
		txtsozlesmeNumarasi.setBounds(225, 58, 250, 20);
		yeniPanel.add(txtsozlesmeNumarasi);
	}

	public void sozlesmeNoOlustur() {

		sozlesmeOzel = new JLabel("Sozlesme Ozel Numarasi: ");
		sozlesmeOzel.setSize(100, 30);
		sozlesmeOzel.setBounds(10, 80, 220, 30);
		yeniPanel.add(sozlesmeOzel);
		txtsozlesmeOzel = new JTextField();
		txtsozlesmeOzel.setSize(250, 20);
		txtsozlesmeOzel.setBounds(225, 88, 250, 20);
		yeniPanel.add(txtsozlesmeOzel);

	}

	public void temsilciAdiOlustur() {

		temsilci = new JLabel("Temsilci Adi: ");
		temsilci.setSize(100, 30);
		temsilci.setBounds(10, 110, 220, 30);
		yeniPanel.add(temsilci);
		txttemsilci = new JTextField();
		txttemsilci.setSize(250, 20);
		txttemsilci.setBounds(225, 118, 250, 20);
		yeniPanel.add(txttemsilci);

	}

	public void sMusteriIDOlustur() {

		sozlesmeMusteri = new JLabel("Sozlesme Yapilan Musteri ID:");
		sozlesmeMusteri.setSize(100, 30);
		sozlesmeMusteri.setBounds(10, 140, 220, 30);
		yeniPanel.add(sozlesmeMusteri);
		txtsozlesmeMusteri = new JTextField();
		txtsozlesmeMusteri.setSize(250, 20);
		txtsozlesmeMusteri.setBounds(225, 148, 250, 20);
		yeniPanel.add(txtsozlesmeMusteri);

	}

	public void yeniKayitMenusuSozlesme() {

		container = getContentPane();
		container.removeAll();
		yeniPanel = new JPanel(null);
		yeniPanel.setSize(1100, 600);
		yeniPanel.setBounds(0, 0, 1100, 600);

		yeniPanel.setBackground(Color.white);

		SozlesmeislemIDOlustur();
		sozlesmeNoOlustur();
		temsilciAdiOlustur();
		sMusteriIDOlustur();

		JTextArea txtAnaSayfa = new JTextArea();
		txtAnaSayfa.setText("             SOZLESME BILGILERI");
		Font font = new Font("Default", Font.CENTER_BASELINE, 16);
		txtAnaSayfa.setFont(font);

		// Color color=new
		// Color(115,44,123);//http://www.colorcombos.com/colors/732C7B
		// purple

		txtAnaSayfa.setForeground(Color.BLACK);
		txtAnaSayfa.setSize(460, 40);
		txtAnaSayfa.setBounds(15, 15, 460, 30);

		Color color2 = UIManager.getColor("Panel.background");

		txtAnaSayfa.setBackground(color2);
		txtAnaSayfa.setEditable(false);

		yeniPanel.add(txtAnaSayfa);
		btnKaydet = new JButton(" Kaydet");
		btnKaydet.setSize(100, 30);
		btnKaydet.setBounds(271, 530, 200, 30);

		btnKaydet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sozlesmeYeniKayit();
			}

		});

		yeniPanel.add(btnKaydet);
		container.add(yeniPanel);
		invalidate();
		repaint();
	}

	public void sozlesmeYeniKayit() {

		try {
			resultSozlesme.first();
			resultSozlesme.moveToInsertRow();

			resultSozlesme.updateInt("SozlesmeislemID", Integer.parseInt(txtsozlesmeNumarasi.getText()));
			resultSozlesme.updateInt("sozlesmeNo", Integer.parseInt(txtsozlesmeOzel.getText()));

			resultSozlesme.updateString("temsilciAdi", txttemsilci.getText());
			resultSozlesme.updateInt("sMusteriID", Integer.parseInt(txtsozlesmeMusteri.getText()));

			resultSozlesme.insertRow();

			resultSozlesme.beforeFirst();

			txtsozlesmeNumarasi.setText("");
			txtsozlesmeOzel.setText("");
			txttemsilci.setText("");
			txtsozlesmeMusteri.setText("");

			JOptionPane.showMessageDialog(null, "Sozlesme Kayıt Eklendi", "Sonuç", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, "Sozlesme Kayıt Eklenemedi", "Sonuç", JOptionPane.PLAIN_MESSAGE);
		}

	}

	public void listelemeYapSozlesme(String sqlKodu) throws SQLException {

		container = getContentPane();
		container.removeAll();
		yeniPanel = new JPanel(null);
		yeniPanel.setSize(1090, 590);
		yeniPanel.setBounds(0, 0, 1090, 590);
		yeniPanel.setBackground(Color.white);
		String[] tabloBasliklari = { "Sozlesme islem ID", "Sozlesme Ozel Numarasi", "Temsilci Adi",
				"Sozlesme Imzalayan Musteri ID" };

		resultSozlesme = statement11.executeQuery(sqlKodu);

		resultSozlesme.last();

		int stokSayisi = resultSozlesme.getRow();
		resultSozlesme.first();
		String[][] veri = new String[stokSayisi][9];
		for (int i = 0; i < stokSayisi; i++) {
			veri[i][0] = Integer.toString(resultSozlesme.getInt("SozlesmeislemID"));
			veri[i][1] = Integer.toString(resultSozlesme.getInt("sozlesmeNo"));
			veri[i][2] = resultSozlesme.getString("temsilciAdi");
			veri[i][3] = Integer.toString(resultSozlesme.getInt("sMusteriID"));

			resultSozlesme.next();
		}

		tabloSozlesme = new JTable(stokSayisi, 9);
		tabloSozlesme.setBackground(Color.white);
		tabloSozlesme.setSize(1050, 672);
		tabloSozlesme.setBounds(0, 0, 1050, 672);
		// tablo.setFont(new Font("Serif", Font.BOLD, 20));
		tabloSozlesme.setModel(new DefaultTableModel(veri, tabloBasliklari));
		JScrollPane panel = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		panel.setBounds(0, 0, 1095, 5672);
		panel.getViewport().add(tabloSozlesme);
		// JTableHeader baslik = tablo.getTableHeader();
		// tablo.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		yeniPanel.add(panel);
		yeniPanel.setBackground(Color.lightGray);
		container.add(yeniPanel);
		invalidate();
		repaint();
	}

	public void guncellemeMenusuSozlesme() {

		icerikArama("Güncellenecek Sozlesme ID");
		guncelleSec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					container = getContentPane();
					container.removeAll();
					yeniPanel = new JPanel(null);
					yeniPanel.setSize(1100, 590);
					yeniPanel.setBounds(0, 0, 1100, 590);
					yeniPanel.setBackground(Color.white);
					resultSozlesme = statement11.executeQuery("SELECT * FROM sozlesme WHERE SozlesmeislemID =  "
							+ Integer.parseInt(txtGuncelle.getText()));
					resultSozlesme.first();

					SozlesmeislemIDOlustur();
					txtsozlesmeNumarasi.setEditable(false);
					txtsozlesmeNumarasi.setText(txtGuncelle.getText());
					sozlesmeNoOlustur();
					txtsozlesmeOzel.setText(resultSozlesme.getString("sozlesmeNo"));
					temsilciAdiOlustur();
					txttemsilci.setText(resultSozlesme.getString("temsilciAdi"));
					sMusteriIDOlustur();
					txtsozlesmeMusteri.setText(resultSozlesme.getString("sMusteriID"));

					JButton btnGuncelle = new JButton("Güncelle");
					btnGuncelle.setSize(70, 30);
					btnGuncelle.setBounds(271, 330, 100, 30);
					yeniPanel.add(btnGuncelle);
					btnGuncelle.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try {
								resultSozlesme.updateInt("sozlesmeNo", Integer.parseInt(txtsozlesmeOzel.getText()));
								resultSozlesme.updateString("temsilciAdi", (txttemsilci.getText()));
								resultSozlesme.updateInt("sMusteriID", Integer.parseInt(txtsozlesmeMusteri.getText()));

								resultSozlesme.updateRow();
								resultSozlesme = statement11.executeQuery("SELECT * FROM sozlesme");
								JOptionPane.showMessageDialog(null, "Kayıt Güncellendi", "Sonuç",
										JOptionPane.INFORMATION_MESSAGE);
							} catch (SQLException ex) {
								JOptionPane.showConfirmDialog(null, "Güncelleme sırasında hata olustu.", "Sonuc",
										JOptionPane.PLAIN_MESSAGE);
							}
						}
					});
					container.add(yeniPanel);
					invalidate();
					repaint();
				} catch (SQLException ex) {
					Logger.getLogger(arabaKirala.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		});

	}

	public void sozlesmeSil() {

		icerikArama("Silinecek Sozlesmenin ID Numarası");
		guncelleSec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					resultSozlesme = statement.executeQuery("SELECT * FROM sozlesme WHERE SozlesmeislemID = "
							+ Integer.parseInt(txtGuncelle.getText()));
					resultSozlesme.first();
					resultSozlesme.deleteRow();
					resultSozlesme = statement11.executeQuery("SELECT * FROM sozlesme");

					resultIslem = statement9.executeQuery(
							"SELECT * FROM islem WHERE islemID = " + Integer.parseInt(txtGuncelle.getText()));
					resultIslem.first();
					resultIslem.deleteRow();
					resultIslem = statement.executeQuery("SELECT * FROM islem");

					JOptionPane.showMessageDialog(null, "Kayıt Silindi", "Sonuç", JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException ex) {
					Logger.getLogger(arabaKirala.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		});

	}

	public void aramaMenusuSozlesme() {

		container = getContentPane();
		container.removeAll();
		yeniPanel = new JPanel(null);
		yeniPanel.setSize(1100, 590);
		yeniPanel.setBounds(0, 0, 1100, 590);
		yeniPanel.setBackground(Color.white);
		String[] secenekler = { "Sozlesme ID ye Gore Ara", "Sozlesme Ozel Numaraya Gore Ara", "Temsilci Adina Gore Ara",
				"Sozlesme Imzalanan Musteriye Gore Ara" };

		final JComboBox aramaSecenekleri = new JComboBox(secenekler);
		aramaSecenekleri.setSize(250, 25);
		aramaSecenekleri.setBounds(10, 50, 250, 25);
		yeniPanel.add(aramaSecenekleri);
		JButton sec = new JButton("Seç");
		sec.setSize(70, 25);
		sec.setBounds(270, 50, 70, 25);
		sec.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				switch (aramaSecenekleri.getSelectedIndex()) {
				case 0:
					icerikListelemeSozlesme("Sozlesme ID giriniz: ", "SozlesmeislemID");
					break;
				case 1:
					icerikListelemeSozlesme("Sozlesme Ozel Numarasini giriniz: ", "sozlesmeNo");
					break;
				case 2:
					icerikListelemeSozlesme("Temsilci Adi giriniz: ", "temsilciAdi");
					break;
				case 3:
					icerikListelemeSozlesme("Sozlesme Imzalanan Musteri ID giriniz: ", "sMusteriID");
					break;

				}
			}
		});

		yeniPanel.add(sec);
		container.add(yeniPanel);
		invalidate();
		repaint();
	}

	public void icerikListelemeSozlesme(String mesaj, final String sutun) {

		container = getContentPane();
		container.removeAll();
		icerikArama(mesaj);

		guncelleSec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (sutun.equals("SozlesmeislemID") | sutun.equals("sozlesmeNo") | sutun.equals("sMusteriID"))
						listelemeYapSozlesme("SELECT * FROM sozlesme WHERE " + sutun + "=" + txtGuncelle.getText());
					else
						listelemeYapSozlesme(
								"SELECT * FROM sozlesme WHERE " + sutun + " LIKE" + "'" + txtGuncelle.getText() + "'");

				} catch (SQLException ex) {
					JOptionPane.showConfirmDialog(null, "Baglantı Basarısız Sozlesme Arama", "Postgresql Baglantısı",
							JOptionPane.PLAIN_MESSAGE);
				}
			}
		}

		);

		yeniPanel = new JPanel(null);
		yeniPanel.setSize(1090, 590);
		yeniPanel.setBounds(0, 0, 1090, 590);
		yeniPanel.setBackground(Color.white);
		container.add(yeniPanel);
		invalidate();
		repaint();

	}

	public void BakimislemIDOlustur() {

		bakimNumarasi = new JLabel("Bakim ID: ");
		bakimNumarasi.setSize(100, 30);
		bakimNumarasi.setBounds(10, 50, 190, 30);
		yeniPanel.add(bakimNumarasi);
		txtbakimNumarasi = new JTextField();
		txtbakimNumarasi.setSize(250, 20);
		txtbakimNumarasi.setBounds(195, 58, 250, 20);
		yeniPanel.add(txtbakimNumarasi);
	}

	public void bakimYapilanYerOlustur() {

		bakimYeri = new JLabel("Bakim Yapilan Yer: ");
		bakimYeri.setSize(100, 30);
		bakimYeri.setBounds(10, 80, 190, 30);
		yeniPanel.add(bakimYeri);
		txtbakimYeri = new JTextField();
		txtbakimYeri.setSize(250, 20);
		txtbakimYeri.setBounds(195, 88, 250, 20);
		yeniPanel.add(txtbakimYeri);

	}

	public void bakimdaYapilanIslemOlustur() {

		bakimdaYapilanIslem = new JLabel("Yapilan Islem: ");
		bakimdaYapilanIslem.setSize(100, 30);
		bakimdaYapilanIslem.setBounds(10, 110, 190, 30);
		yeniPanel.add(bakimdaYapilanIslem);
		txtbakimdaYapilanIslem = new JTextField();
		txtbakimdaYapilanIslem.setSize(250, 20);
		txtbakimdaYapilanIslem.setBounds(195, 118, 250, 20);
		yeniPanel.add(txtbakimdaYapilanIslem);

	}

	public void bAracIDOlustur() {

		bakimArac = new JLabel("Bakim Yapilan Arac ID:");
		bakimArac.setSize(100, 30);
		bakimArac.setBounds(10, 140, 190, 30);
		yeniPanel.add(bakimArac);
		txtbakimArac = new JTextField();
		txtbakimArac.setSize(250, 20);
		txtbakimArac.setBounds(195, 148, 250, 20);
		yeniPanel.add(txtbakimArac);

	}

	public void yeniKayitMenusuBakim() {

		container = getContentPane();
		container.removeAll();
		yeniPanel = new JPanel(null);
		yeniPanel.setSize(1100, 600);
		yeniPanel.setBounds(0, 0, 1100, 600);

		yeniPanel.setBackground(Color.white);

		BakimislemIDOlustur();
		bakimYapilanYerOlustur();
		bakimdaYapilanIslemOlustur();
		bAracIDOlustur();

		JTextArea txtAnaSayfa = new JTextArea();
		txtAnaSayfa.setText("             ARAC BAKIM BILGILERI");
		Font font = new Font("Default", Font.CENTER_BASELINE, 16);
		txtAnaSayfa.setFont(font);

		// Color color=new
		// Color(115,44,123);//http://www.colorcombos.com/colors/732C7B
		// purple

		txtAnaSayfa.setForeground(Color.BLACK);
		txtAnaSayfa.setSize(460, 40);
		txtAnaSayfa.setBounds(15, 15, 460, 30);

		Color color2 = UIManager.getColor("Panel.background");

		txtAnaSayfa.setBackground(color2);
		txtAnaSayfa.setEditable(false);

		yeniPanel.add(txtAnaSayfa);
		btnKaydet = new JButton(" Kaydet");
		btnKaydet.setSize(100, 30);
		btnKaydet.setBounds(271, 530, 200, 30);

		btnKaydet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bakimYeniKayit();
			}

		});

		yeniPanel.add(btnKaydet);
		container.add(yeniPanel);
		invalidate();
		repaint();
	}

	public void bakimYeniKayit() {

		try {
			resultBakim.first();
			resultBakim.moveToInsertRow();

			resultBakim.updateInt("BakimislemID", Integer.parseInt(txtbakimNumarasi.getText()));
			resultBakim.updateString("bakimYapilanYer", txtbakimYeri.getText());
			resultBakim.updateString("bakimdaYapilanIslem", txtbakimdaYapilanIslem.getText());
			resultBakim.updateInt("bAracID", Integer.parseInt(txtbakimArac.getText()));

			resultBakim.insertRow();

			resultBakim.beforeFirst();

			txtbakimNumarasi.setText("");
			txtbakimYeri.setText("");
			txtbakimdaYapilanIslem.setText("");
			txtbakimArac.setText("");

			JOptionPane.showMessageDialog(null, "Bakim Bilgileri Kayıt Eklendi", "Sonuç",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, "Bakim Bilgileri Kayıt Eklenemedi", "Sonuç", JOptionPane.PLAIN_MESSAGE);
		}

	}

	public void listelemeYapBakim(String sqlKodu) throws SQLException {

		container = getContentPane();
		container.removeAll();
		yeniPanel = new JPanel(null);
		yeniPanel.setSize(1090, 590);
		yeniPanel.setBounds(0, 0, 1090, 590);
		yeniPanel.setBackground(Color.white);
		String[] tabloBasliklari = { "Bakim ID", "Bakimin Yapildigi Yer", "Bakimda Yapilan Islem",
				"Bakim Yapilan Arac ID" };

		resultBakim = statement12.executeQuery(sqlKodu);

		resultBakim.last();

		int stokSayisi = resultBakim.getRow();
		resultBakim.first();
		String[][] veri = new String[stokSayisi][9];
		for (int i = 0; i < stokSayisi; i++) {
			veri[i][0] = Integer.toString(resultBakim.getInt("BakimislemID"));
			veri[i][1] = resultBakim.getString("bakimYapilanYer");
			veri[i][2] = resultBakim.getString("bakimdaYapilanIslem");
			veri[i][3] = Integer.toString(resultBakim.getInt("bAracID"));

			resultBakim.next();
		}

		tabloBakim = new JTable(stokSayisi, 9);
		tabloBakim.setBackground(Color.white);
		tabloBakim.setSize(1050, 672);
		tabloBakim.setBounds(0, 0, 1050, 672);
		// tablo.setFont(new Font("Serif", Font.BOLD, 20));
		tabloBakim.setModel(new DefaultTableModel(veri, tabloBasliklari));
		JScrollPane panel = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		panel.setBounds(0, 0, 1095, 5672);
		panel.getViewport().add(tabloBakim);
		// JTableHeader baslik = tablo.getTableHeader();
		// tablo.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		yeniPanel.add(panel);
		yeniPanel.setBackground(Color.lightGray);
		container.add(yeniPanel);
		invalidate();
		repaint();
	}

	public void guncellemeMenusuBakim() {

		icerikArama("Güncellenecek Bakim ID");
		guncelleSec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					container = getContentPane();
					container.removeAll();
					yeniPanel = new JPanel(null);
					yeniPanel.setSize(1100, 590);
					yeniPanel.setBounds(0, 0, 1100, 590);
					yeniPanel.setBackground(Color.white);
					resultBakim = statement12.executeQuery(
							"SELECT * FROM bakim WHERE BakimislemID =  " + Integer.parseInt(txtGuncelle.getText()));
					resultBakim.first();

					BakimislemIDOlustur();
					txtbakimNumarasi.setEditable(false);
					txtbakimNumarasi.setText(txtGuncelle.getText());
					bakimYapilanYerOlustur();
					txtbakimYeri.setText(resultBakim.getString("bakimYapilanYer"));

					bakimdaYapilanIslemOlustur();
					txtbakimdaYapilanIslem.setText(resultBakim.getString("bakimdaYapilanIslem"));

					bAracIDOlustur();
					txtbakimArac.setText(resultBakim.getString("bAracID"));

					JButton btnGuncelle = new JButton("Güncelle");
					btnGuncelle.setSize(70, 30);
					btnGuncelle.setBounds(271, 330, 100, 30);
					yeniPanel.add(btnGuncelle);
					btnGuncelle.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try {
								resultBakim.updateString("bakimYapilanYer", (txtbakimYeri.getText()));
								resultBakim.updateString("bakimdaYapilanIslem", (txtbakimdaYapilanIslem.getText()));
								resultBakim.updateInt("bAracID", Integer.parseInt(txtbakimArac.getText()));

								resultBakim.updateRow();
								resultBakim = statement12.executeQuery("SELECT * FROM bakim");
								JOptionPane.showMessageDialog(null, "Kayıt Güncellendi", "Sonuç",
										JOptionPane.INFORMATION_MESSAGE);
							} catch (SQLException ex) {
								JOptionPane.showConfirmDialog(null, "Güncelleme sırasında hata olustu.", "Sonuc",
										JOptionPane.PLAIN_MESSAGE);
							}
						}
					});
					container.add(yeniPanel);
					invalidate();
					repaint();
				} catch (SQLException ex) {
					Logger.getLogger(arabaKirala.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		});

	}

	public void bakimSil() {

		icerikArama("Silinecek Bakim ID Numarası");
		guncelleSec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					resultBakim = statement.executeQuery(
							"SELECT * FROM bakim WHERE BakimislemID = " + Integer.parseInt(txtGuncelle.getText()));
					resultBakim.first();
					resultBakim.deleteRow();
					resultBakim = statement12.executeQuery("SELECT * FROM bakim");

					resultIslem = statement9.executeQuery(
							"SELECT * FROM islem WHERE islemID = " + Integer.parseInt(txtGuncelle.getText()));
					resultIslem.first();
					resultIslem.deleteRow();
					resultIslem = statement.executeQuery("SELECT * FROM islem");

					JOptionPane.showMessageDialog(null, "Kayıt Silindi", "Sonuç", JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException ex) {
					Logger.getLogger(arabaKirala.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		});

	}

	public void aramaMenusuBakim() {

		container = getContentPane();
		container.removeAll();
		yeniPanel = new JPanel(null);
		yeniPanel.setSize(1100, 590);
		yeniPanel.setBounds(0, 0, 1100, 590);
		yeniPanel.setBackground(Color.white);
		String[] secenekler = { "Bakim ID ye Gore Ara", "Bakim Yapilan Yere Gore Ara",
				"Bakimda Yapilan Isleme Gore Ara", "Bakim Yapilan Araca Gore Ara" };

		final JComboBox aramaSecenekleri = new JComboBox(secenekler);
		aramaSecenekleri.setSize(250, 25);
		aramaSecenekleri.setBounds(10, 50, 250, 25);
		yeniPanel.add(aramaSecenekleri);
		JButton sec = new JButton("Seç");
		sec.setSize(70, 25);
		sec.setBounds(270, 50, 70, 25);
		sec.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				switch (aramaSecenekleri.getSelectedIndex()) {
				case 0:
					icerikListelemeBakim("Bakim ID giriniz: ", "BakimislemID");
					break;
				case 1:
					icerikListelemeBakim("Bakim Yapilan Yeri giriniz: ", "bakimYapilanYer");
					break;
				case 2:
					icerikListelemeBakim("Bakimda Yapilan Islemi giriniz: ", "bakimdaYapilanIslem");
					break;
				case 3:
					icerikListelemeBakim("Bakim Yapilan Arac ID giriniz: ", "bAracID");
					break;

				}
			}
		});

		yeniPanel.add(sec);
		container.add(yeniPanel);
		invalidate();
		repaint();
	}

	public void icerikListelemeBakim(String mesaj, final String sutun) {

		container = getContentPane();
		container.removeAll();
		icerikArama(mesaj);

		guncelleSec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (sutun.equals("BakimislemID") | sutun.equals("bAracID"))
						listelemeYapBakim("SELECT * FROM bakim WHERE " + sutun + "=" + txtGuncelle.getText());
					else
						listelemeYapBakim(
								"SELECT * FROM bakim WHERE " + sutun + " LIKE" + "'" + txtGuncelle.getText() + "'");

				} catch (SQLException ex) {
					JOptionPane.showConfirmDialog(null, "Baglantı Basarısız Bakim Arama", "Postgresql Baglantısı",
							JOptionPane.PLAIN_MESSAGE);
				}
			}
		}

		);

		yeniPanel = new JPanel(null);
		yeniPanel.setSize(1090, 590);
		yeniPanel.setBounds(0, 0, 1090, 590);
		yeniPanel.setBackground(Color.white);
		container.add(yeniPanel);
		invalidate();
		repaint();

	}

	public void OdemeislemIDOlustur() {

		odemeNumarasi = new JLabel("Odeme ID: ");
		odemeNumarasi.setSize(100, 30);
		odemeNumarasi.setBounds(10, 50, 190, 30);
		yeniPanel.add(odemeNumarasi);
		txtodemeNumarasi = new JTextField();
		txtodemeNumarasi.setSize(250, 20);
		txtodemeNumarasi.setBounds(195, 58, 250, 20);
		yeniPanel.add(txtodemeNumarasi);
	}

	public void odemeSekliOlustur() {

		odemeSekil = new JLabel("Odeme Sekli: ");
		odemeSekil.setSize(100, 30);
		odemeSekil.setBounds(10, 80, 190, 30);
		yeniPanel.add(odemeSekil);
		txtodemeSekil = new JTextField();
		txtodemeSekil.setSize(250, 20);
		txtodemeSekil.setBounds(195, 88, 250, 20);
		yeniPanel.add(txtodemeSekil);

	}

	public void odemeTutariOlustur() {

		odemeTutar = new JLabel("Odeme Tutari: ");
		odemeTutar.setSize(100, 30);
		odemeTutar.setBounds(10, 110, 190, 30);
		yeniPanel.add(odemeTutar);
		txtodemeTutar = new JTextField();
		txtodemeTutar.setSize(250, 20);
		txtodemeTutar.setBounds(195, 118, 250, 20);
		yeniPanel.add(txtodemeTutar);

	}

	public void oMusteriIDOlustur() {

		odemeMusteri = new JLabel("Odeme Yapan Musteri ID:");
		odemeMusteri.setSize(100, 30);
		odemeMusteri.setBounds(10, 140, 190, 30);
		yeniPanel.add(odemeMusteri);
		txtodemeMusteri = new JTextField();
		txtodemeMusteri.setSize(250, 20);
		txtodemeMusteri.setBounds(195, 148, 250, 20);
		yeniPanel.add(txtodemeMusteri);

	}

	public void yeniKayitMenusuOdeme() {

		container = getContentPane();
		container.removeAll();
		yeniPanel = new JPanel(null);
		yeniPanel.setSize(1100, 600);
		yeniPanel.setBounds(0, 0, 1100, 600);

		yeniPanel.setBackground(Color.white);

		OdemeislemIDOlustur();
		odemeSekliOlustur();
		odemeTutariOlustur();
		oMusteriIDOlustur();

		JTextArea txtAnaSayfa = new JTextArea();
		txtAnaSayfa.setText("             ODEME BILGILERI");
		Font font = new Font("Default", Font.CENTER_BASELINE, 16);
		txtAnaSayfa.setFont(font);

		// Color color=new
		// Color(115,44,123);//http://www.colorcombos.com/colors/732C7B
		// purple

		txtAnaSayfa.setForeground(Color.BLACK);
		txtAnaSayfa.setSize(460, 40);
		txtAnaSayfa.setBounds(15, 15, 460, 30);

		Color color2 = UIManager.getColor("Panel.background");

		txtAnaSayfa.setBackground(color2);
		txtAnaSayfa.setEditable(false);

		yeniPanel.add(txtAnaSayfa);
		btnKaydet = new JButton(" Kaydet");
		btnKaydet.setSize(100, 30);
		btnKaydet.setBounds(271, 530, 200, 30);

		btnKaydet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				odemeYeniKayit();
			}

		});

		yeniPanel.add(btnKaydet);
		container.add(yeniPanel);
		invalidate();
		repaint();
	}

	public void odemeYeniKayit() {

		try {
			resultOdeme.first();
			resultOdeme.moveToInsertRow();

			resultOdeme.updateInt("OdemeislemID", Integer.parseInt(txtodemeNumarasi.getText()));
			resultOdeme.updateString("odemeSekli", txtodemeSekil.getText());
			resultOdeme.updateString("odemeTutari", txtodemeTutar.getText());
			resultOdeme.updateInt("oMusteriID", Integer.parseInt(txtodemeMusteri.getText()));

			resultOdeme.insertRow();

			resultOdeme.beforeFirst();

			txtodemeNumarasi.setText("");
			txtodemeSekil.setText("");
			txtodemeTutar.setText("");
			txtodemeMusteri.setText("");

			JOptionPane.showMessageDialog(null, "Odeme Kayıt Eklendi", "Sonuç", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, "Odeme Kayıt Eklenemedi", "Sonuç", JOptionPane.PLAIN_MESSAGE);
		}

	}

	public void listelemeYapOdeme(String sqlKodu) throws SQLException {

		container = getContentPane();
		container.removeAll();
		yeniPanel = new JPanel(null);
		yeniPanel.setSize(1090, 590);
		yeniPanel.setBounds(0, 0, 1090, 590);
		yeniPanel.setBackground(Color.white);
		String[] tabloBasliklari = { "Odeme ID", "Odeme Sekli", "Odeme Tutari", "Odeme Yapan Musteri" };

		resultOdeme = statement13.executeQuery(sqlKodu);

		resultOdeme.last();

		int stokSayisi = resultOdeme.getRow();
		resultOdeme.first();
		String[][] veri = new String[stokSayisi][9];
		for (int i = 0; i < stokSayisi; i++) {
			veri[i][0] = Integer.toString(resultOdeme.getInt("OdemeislemID"));
			veri[i][1] = resultOdeme.getString("odemeSekli");
			veri[i][2] = resultOdeme.getString("odemeTutari");
			veri[i][3] = Integer.toString(resultOdeme.getInt("oMusteriID"));

			resultOdeme.next();
		}

		tabloOdeme = new JTable(stokSayisi, 9);
		tabloOdeme.setBackground(Color.white);
		tabloOdeme.setSize(1050, 672);
		tabloOdeme.setBounds(0, 0, 1050, 672);
		// tablo.setFont(new Font("Serif", Font.BOLD, 20));
		tabloOdeme.setModel(new DefaultTableModel(veri, tabloBasliklari));
		JScrollPane panel = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		panel.setBounds(0, 0, 1095, 5672);
		panel.getViewport().add(tabloOdeme);
		// JTableHeader baslik = tablo.getTableHeader();
		// tablo.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		yeniPanel.add(panel);
		yeniPanel.setBackground(Color.lightGray);
		container.add(yeniPanel);
		invalidate();
		repaint();
	}

	public void guncellemeMenusuOdeme() {

		icerikArama("Güncellenecek Odeme ID");
		guncelleSec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					container = getContentPane();
					container.removeAll();
					yeniPanel = new JPanel(null);
					yeniPanel.setSize(1100, 590);
					yeniPanel.setBounds(0, 0, 1100, 590);
					yeniPanel.setBackground(Color.white);
					resultOdeme = statement13.executeQuery(
							"SELECT * FROM odeme WHERE OdemeislemID =  " + Integer.parseInt(txtGuncelle.getText()));
					resultOdeme.first();

					OdemeislemIDOlustur();
					txtodemeNumarasi.setEditable(false);
					txtodemeNumarasi.setText(txtGuncelle.getText());
					odemeSekliOlustur();
					txtodemeSekil.setText(resultOdeme.getString("odemeSekli"));

					odemeTutariOlustur();
					txtodemeTutar.setText(resultOdeme.getString("odemeTutari"));

					oMusteriIDOlustur();
					txtodemeMusteri.setText(resultOdeme.getString("oMusteriID"));

					JButton btnGuncelle = new JButton("Güncelle");
					btnGuncelle.setSize(70, 30);
					btnGuncelle.setBounds(271, 330, 100, 30);
					yeniPanel.add(btnGuncelle);
					btnGuncelle.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try {
								resultOdeme.updateString("odemeSekli", (txtodemeSekil.getText()));
								resultOdeme.updateString("odemeTutari", (txtodemeTutar.getText()));
								resultOdeme.updateInt("oMusteriID", Integer.parseInt(txtodemeMusteri.getText()));

								resultOdeme.updateRow();
								resultOdeme = statement13.executeQuery("SELECT * FROM odeme");
								JOptionPane.showMessageDialog(null, "Kayıt Güncellendi", "Sonuç",
										JOptionPane.INFORMATION_MESSAGE);
							} catch (SQLException ex) {
								JOptionPane.showConfirmDialog(null, "Güncelleme sırasında hata olustu.", "Sonuc",
										JOptionPane.PLAIN_MESSAGE);
							}
						}
					});
					container.add(yeniPanel);
					invalidate();
					repaint();
				} catch (SQLException ex) {
					Logger.getLogger(arabaKirala.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		});

	}

	public void odemeSil() {

		icerikArama("Silinecek Odeme ID Numarası");
		guncelleSec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					resultOdeme = statement.executeQuery(
							"SELECT * FROM odeme WHERE OdemeislemID = " + Integer.parseInt(txtGuncelle.getText()));
					resultOdeme.first();
					resultOdeme.deleteRow();
					resultOdeme = statement13.executeQuery("SELECT * FROM odeme");

					resultIslem = statement9.executeQuery(
							"SELECT * FROM islem WHERE islemID = " + Integer.parseInt(txtGuncelle.getText()));
					resultIslem.first();
					resultIslem.deleteRow();
					resultIslem = statement.executeQuery("SELECT * FROM islem");

					JOptionPane.showMessageDialog(null, "Kayıt Silindi", "Sonuç", JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException ex) {
					Logger.getLogger(arabaKirala.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		});

	}

	public void aramaMenusuOdeme() {

		container = getContentPane();
		container.removeAll();
		yeniPanel = new JPanel(null);
		yeniPanel.setSize(1100, 590);
		yeniPanel.setBounds(0, 0, 1100, 590);
		yeniPanel.setBackground(Color.white);
		String[] secenekler = { "Odeme ID ye Gore Ara", "Odeme Sekline Gore Ara", "Odeme Tutarina Gore Ara",
				"Odeme Yapan Musteriye Gore Ara" };

		final JComboBox aramaSecenekleri = new JComboBox(secenekler);
		aramaSecenekleri.setSize(250, 25);
		aramaSecenekleri.setBounds(10, 50, 250, 25);
		yeniPanel.add(aramaSecenekleri);
		JButton sec = new JButton("Seç");
		sec.setSize(70, 25);
		sec.setBounds(270, 50, 70, 25);
		sec.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				switch (aramaSecenekleri.getSelectedIndex()) {
				case 0:
					icerikListelemeOdeme("Odeme ID giriniz: ", "OdemeislemID");
					break;
				case 1:
					icerikListelemeOdeme("Odeme Sekli giriniz: ", "odemeSekli");
					break;
				case 2:
					icerikListelemeOdeme("Odeme Tutarini giriniz: ", "odemeTutari");
					break;
				case 3:
					icerikListelemeOdeme("Odeme Yapan Musteri ID giriniz: ", "oMusteriID");
					break;

				}
			}
		});

		yeniPanel.add(sec);
		container.add(yeniPanel);
		invalidate();
		repaint();
	}

	public void icerikListelemeOdeme(String mesaj, final String sutun) {

		container = getContentPane();
		container.removeAll();
		icerikArama(mesaj);

		guncelleSec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (sutun.equals("OdemeislemID") | sutun.equals("oMusteriID"))
						listelemeYapOdeme("SELECT * FROM odeme WHERE " + sutun + "=" + txtGuncelle.getText());
					else
						listelemeYapOdeme(
								"SELECT * FROM odeme WHERE " + sutun + " LIKE" + "'" + txtGuncelle.getText() + "'");

				} catch (SQLException ex) {
					JOptionPane.showConfirmDialog(null, "Baglantı Basarısız Odeme Arama", "Postgresql Baglantısı",
							JOptionPane.PLAIN_MESSAGE);
				}
			}
		}

		);

		yeniPanel = new JPanel(null);
		yeniPanel.setSize(1090, 590);
		yeniPanel.setBounds(0, 0, 1090, 590);
		yeniPanel.setBackground(Color.white);
		container.add(yeniPanel);
		invalidate();
		repaint();

	}

	/*
	 * *************************************************************************
	 * *************************************************************************
	 * *************************************************************************
	 * *************************************************************************
	 * *************************************************************************
	 * *************************************************************************
	 * *************************************************************************
	 * *************************************************************************
	 * *************************************************************************
	 * *************************************************************************
	 * *************************************************************************
	 * *************************************************************************
	 */

	public void hakkindaMenusu() {

		container = getContentPane();
		container.removeAll();
		yeniPanel = new JPanel(null);
		yeniPanel.setSize(1100, 600);
		yeniPanel.setBounds(0, 0, 700, 500);

		yeniPanel.setBackground(Color.white);

		JTextArea txtAnaSayfa = new JTextArea();
		txtAnaSayfa.setText("             	HAKKINDA");
		Font font = new Font("Default", Font.CENTER_BASELINE, 16);
		txtAnaSayfa.setFont(font);

		// Color color=new
		// Color(115,44,123);//http://www.colorcombos.com/colors/732C7B
		// purple

		txtAnaSayfa.setForeground(Color.BLACK);
		txtAnaSayfa.setSize(460, 40);
		txtAnaSayfa.setBounds(15, 15, 460, 30);

		Color color2 = UIManager.getColor("Panel.background");

		txtAnaSayfa.setBackground(color2);
		txtAnaSayfa.setEditable(false);

		yeniPanel.add(txtAnaSayfa);

		container.add(yeniPanel);
		invalidate();
		repaint();
	}

	public void acilisEkrani() {

		container = getContentPane();
		container.removeAll();

		// ImageIcon resim = new ImageIcon("/home/seray/Desktop/resim3.jpg");

		yeniPanel = new JPanel(null);
		yeniPanel.setSize(1100, 590);

		yeniPanel.setBounds(0, 0, 1100, 590);

		// Image image = resim.getImage();
		// Image newimg = image.getScaledInstance(1100, 800,
		// java.awt.Image.SCALE_SMOOTH);
		// scale it the smooth way
		// resim = new ImageIcon(newimg);

		// JLabel resimEkle = new JLabel(resim);
		// resimEkle.setSize(1100, 800);
		// resimEkle.setBackground(Color.white);
		// resimEkle.setBounds(0, 60, 1100, 800);

		// yeniPanel.add(resimEkle);

		JTextArea txtAnaSayfa = new JTextArea();
		txtAnaSayfa.setText("             ARAC   KIRALAMA  SISTEMI  " + "\n\n"
				+ "\n\n1- ARAC Menusu = Sisteme Arac Ekler - Listeler - Gunceller - Arama Yapar \n"
				+ " \n2- ARAC DURUM Menusu = Araclarin Durum Bilgilerini Tutar - Uygun - Arizali -Bakimda - Kirada gibi.\n"
				+ "                          Durum Bilgileri eklenir-  guncellenebilir - silinebilir - arama yapilabilir\n"
				+ " \n3- ARABA, ISMAKINESI Menuleri = Her Arac bir araba veya is makinesidir\n"
				+ "                                   Ekler, Siler, Gunceller, Listeler, Arama Yapar\n"
				+ "                                   Mesela Araba silersen ayni id li arac da silinir.\n"
				+ "                                   Araba eklenmeden once Arac Eklenmelidir.\n"
				+ " \n4- MUSTERI Menusu = Sisteme Musteri Ekler - Listeler - Gunceller - Arama Yapar\n"
				+ " \n5- KISI, SIRKET Menuleri = Her Musteri ya Kisidir ya da Sirkettir\n"
				+ "                              Ekler, Siler, Gunceller, Listeler, Arama Yapar\n"
				+ "                              Mesela Kisi silersen ayni id li Musteri Silinir.\n"
				+ "	     Kisi eklemeden once Musteri eklenmelidir.\n"
				+ " \n6- ARAC KIRALA Menusu = Arac Kiralanır - Arac Teslim Alinir - Kiralanan Araclar Listelenir - Arama Yapılır\n"
				+ "                           Bir Musteri birden fazla Arac Kiralayabilir.\n"
				+ "                           Bir Arac bir musteriye tahsis edilir.\n"
				+ "\n7- ISLEM Menusu = Sisteme Islem Ekler- Listeler - Gunceller - Arama Yapar\n"
				+ "\n8- TESLIMAT, SOZLESME, BAKIM, ODEME Menusu = Teslimat, Sozlesme, Bakim, Odeme birer Islemdir.\n"
				+ "                        		  Ekler, Siler, Gunceller, Listeler, Arama Yapar\n"
				+ "	    		  Mesela Sozlesme silersen ayni id li Islem Silinir.\n"
				+ "	     		 Sozlesme eklemeden once Islem eklenmelidir.\n");
		Font font = new Font("Default", Font.BOLD, 12);
		txtAnaSayfa.setFont(font);

		// Color color=new
		// Color(115,44,123);//http://www.colorcombos.com/colors/732C7B
		// purple

		txtAnaSayfa.setForeground(Color.BLACK);
		txtAnaSayfa.setSize(850, 600);
		txtAnaSayfa.setBounds(20, 20, 850, 600);

		Color color2 = UIManager.getColor("Panel.background");

		txtAnaSayfa.setBackground(color2);
		txtAnaSayfa.setEditable(false);

		yeniPanel.add(txtAnaSayfa);
		container.add(yeniPanel);
		invalidate();
		repaint();

	}

	public Statement baglanti() throws Exception {

		Class.forName(driver).newInstance();
		connection = DriverManager.getConnection(url, userName, password);
		return connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
	}

	public static void main(String[] args) {

		arabaKirala stok = new arabaKirala();
		stok.setVisible(true);

	}

}
