package com.mouridiyya.bibliomouride;

import com.google.common.collect.Maps;
import com.mouridiyya.bibliomouride.entity.Module;
import com.mouridiyya.bibliomouride.entity.*;
import com.mouridiyya.bibliomouride.model.*;
import com.mouridiyya.bibliomouride.service.*;
import org.assertj.core.util.Lists;
import org.assertj.core.util.Sets;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class BibliomourideApplicationTests {

	@Autowired
	private AuthorService authorService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private DiwanService diwanService;
	@Autowired
	private ModuleService moduleService;
	@Autowired
	private ThemeService themeService;
	@Autowired
	private OeuvreService oeuvreService;
	@Autowired
	private ChapitreService chapitreService;
	@Autowired
	private VersService versService;


	@Test
	void contextLoads() {
	}

	@Test
	void populateDBTest(){
		addAuthorTest();
		addDiwanTest();
		addModuleTest();
		addCategoryTest();
		addThemeTest();

		addDiwanTraductionTest();
		addModuleTraductionTest();
		addCategoryTraductionTest();
		addThemeTraductionTest();

		addOeuvreTest();
		addOeuvreTraductionTest();

	}


	Author addAuthor(Long authorId, String name, String bio, String link, String imageUrl){
		AuthorQuery query = new AuthorQuery();
		query.setAuthorId(authorId);
		query.setName(name);
		query.setBiography(bio);
		query.setLink(link);
		query.setImageUrl(imageUrl);
		return authorService.addUpdateAuthor(query);
	}

	Long getAuthorIdByName(String authorName){
		Author author = authorService.getAuthorByName(authorName);
		return author !=null ? author.getAuthorId():null;
	}

	@Test
	void addAuthorTest(){
		List<Author> savedAuthorList= Lists.newArrayList();

		savedAuthorList.add(addAuthor(getAuthorIdByName("Cheikh Ahmadou Bamba"), "Cheikh Ahmadou Bamba", "Cheikh Ahmadou Bamba Mbacké (Aḥmad ibn Muḥammad ibn Ḥabīb Allāh) dit Khadimou al-Rassoul", null, "https://fr.wikipedia.org/wiki/Ahmadou_Bamba#/media/Fichier:AhmaduBamba.jpg"));
		savedAuthorList.add(addAuthor(getAuthorIdByName("Cheikh Muhammad Moustapha Mbacké"),"Cheikh Muhammad Moustapha Mbacké", "", null, "https://fr.m.wikipedia.org/wiki/Fichier:Serigne_moustapha_mback%C3%A9.jpg"));
		savedAuthorList.add(addAuthor(getAuthorIdByName("Cheikh Muhammad Bachir Mbacké"),"Cheikh Muhammad Bachir Mbacké", "", null, "https://www.igfm.sn/wp-content/uploads/2018/10/Serigne-Bassirou-e1540672398966.jpg"));
		savedAuthorList.add(addAuthor(getAuthorIdByName("S. El Hadj Mbacké Khelcom"),"S. El Hadj Mbacké Khelcom", "", null, null));
		savedAuthorList.add(addAuthor(getAuthorIdByName("Cheikh Moussa Ka"),"Cheikh Moussa Ka", "", null, null));
		savedAuthorList.add(addAuthor(getAuthorIdByName("Cheikh Gueye"),"Cheikh Gueye", "", null, "https://i.ytimg.com/vi/iIcEoq8jMFE/maxresdefault.jpg"));
		savedAuthorList.add(addAuthor(getAuthorIdByName("Mouhamadou Mbacké Diouf"),"Mouhamadou Mbacké Diouf", "", null, null));
		savedAuthorList.add(addAuthor(getAuthorIdByName("Abdoul Aziz Mbacké Majalis"),"Abdoul Aziz Mbacké Majalis", "", null, "https://xalimasn.com/wp-content/uploads/2011/04/Aziz3.jpg"));
		savedAuthorList.add(addAuthor(getAuthorIdByName("Cheikh Abdoul Ahad Mbacké"),"Cheikh Abdoul Ahad Mbacké", "", null, "https://www.emedia.sn/IMG/jpg/d9qjs-gxyae9yxj.jpg"));
		savedAuthorList.add(addAuthor(getAuthorIdByName("Serigne Sam Mbaye"),"Serigne Sam Mbaye", "", null, "https://uploads.podcloud.fr/uploads/covers/8840/d37b/7087/03aa/9a00/95ca/330e/4925/b561/ff9e/big_8840d37b708703aa9a0095ca330e4925b561ff9e.jpg?version=1558498632&"));
		savedAuthorList.add(addAuthor(getAuthorIdByName("Serigne Mayib Gueye"),"Serigne Mayib Gueye", "", null, null));
		savedAuthorList.forEach(savedAuthor->	{
			assertTrue(savedAuthor.getAuthorId() != 0);
		});

	}

	Diwan addDiwan(String name, Boolean isAvailable){
		DiwanQuery query = new DiwanQuery();
		query.setIsAvailable(isAvailable);
		query.setName(name);
		return diwanService.addUpdateDiwan(query);
	}

	@Test
	void addDiwanTest(){

		List<Diwan> savedDiwanList= Lists.newArrayList();

		savedDiwanList.add(addDiwan("Amdah", true));
		savedDiwanList.add(addDiwan("Foulkoul Mashun", true));
		savedDiwanList.add(addDiwan("Quraaniyya", true));
		savedDiwanList.add(addDiwan("Ramadaaniyaat", true));
		savedDiwanList.add(addDiwan("Mawâhibul ilaahiyya", true));

		savedDiwanList.forEach(diwanModule->	{
			assertTrue(diwanModule.getDiwanId() != 0);
		});
	}

	DiwanTraduction addDiwanTraduction(Long diwanId, String codeLangue, String name){
		DiwanTraductionQuery query = new DiwanTraductionQuery();
		query.setDiwanId(diwanId);
		query.setCodeLangue(codeLangue);
		query.setName(name);
		return diwanService.addUpdateDiwanTraduction(query);
	}

	List<DiwanTraduction> addDiwanTraductionList(Long diwanId, Map<String,String> mapTrad){
		List<DiwanTraduction> savedDiwanTradList= Lists.newArrayList();
		if(diwanId!=null){
			for (Map.Entry<String, String> trad : mapTrad.entrySet()) {
				savedDiwanTradList.add(addDiwanTraduction(diwanId, trad.getKey(), trad.getValue()));
			}
		}
		return savedDiwanTradList;
	}

	@Test
	void addDiwanTraductionTest(){

		Diwan diwan = diwanService.findByName("Amdah");
		List<DiwanTraduction> savedDiwanTradList;
		Map<String,String> mapTrad = Maps.newHashMap();
		mapTrad.put("EN", "Diwan Praise");
		mapTrad.put("AR", "ديوان الامداح");
		mapTrad.put("WO", "Diwaanu tagg");
		savedDiwanTradList = addDiwanTraductionList(diwan.getDiwanId(), mapTrad);
		savedDiwanTradList.forEach(savedDiwanTrad->	{
			assertTrue(savedDiwanTrad.getDiwanTradId() != 0);
		});

		diwan = diwanService.findByName("Foulkoul Mashun");
		savedDiwanTradList= Lists.newArrayList();
		mapTrad = Maps.newHashMap();
		mapTrad.put("EN", "Diwan of fulku");
		mapTrad.put("AR", "ديوان الفلك");
		mapTrad.put("WO", "Diwaanu fulku");
		savedDiwanTradList = addDiwanTraductionList(diwan.getDiwanId(), mapTrad);
		savedDiwanTradList.forEach(savedDiwanTrad->	{
			assertTrue(savedDiwanTrad.getDiwanTradId() != 0);
		});

	}

	Module addModule(String name, Boolean isAvailable){
		ModuleQuery query = new ModuleQuery();
		query.setIsAvailable(isAvailable);
		query.setName(name);
		return moduleService.addUpdateModule(query);
	}

	@Test
	void addModuleTest(){
		List<Module> savedModuleList= Lists.newArrayList();

		savedModuleList.add(addModule("Ecrits de Cheikh A. Bamba", true));
		savedModuleList.add(addModule("Oeuvres du Mouridisme", true));
		savedModuleList.add(addModule("Recherche sur le Mouridisme", true));
		savedModuleList.add(addModule("Médiathèque du Mouridisme", true));

		savedModuleList.forEach(savedModule->	{
			assertTrue(savedModule.getModuleId() != 0);
		});
	}

	ModuleTraduction addModuleTraduction(Long moduleId, String codeLangue, String name){
		ModuleTraductionQuery query = new ModuleTraductionQuery();
		query.setModuleId(moduleId);
		query.setCodeLangue(codeLangue);
		query.setName(name);
		return moduleService.addUpdateModuleTraduction(query);
	}

	List<ModuleTraduction> addModuleTraductionList(Long moduleId, Map<String,String> mapTrad){
		List<ModuleTraduction> savedModuleTradList= Lists.newArrayList();
		if(moduleId!=null){
			for (Map.Entry<String, String> trad : mapTrad.entrySet()) {
				savedModuleTradList.add(addModuleTraduction(moduleId, trad.getKey(), trad.getValue()));
			}
		}
		return savedModuleTradList;
	}

	@Test
	void addModuleTraductionTest(){
		Module module = moduleService.findByName("Ecrits de Cheikh A. Bamba");
		List<ModuleTraduction> savedModuleTradList;
		Map<String,String> mapTrad = Maps.newHashMap();
		mapTrad.put("EN", "Sheikh A. Bamba's writings");
		mapTrad.put("AR", "كتابات الشيخ أحمد بمب");
		mapTrad.put("WO", "Mbindum Seex Ahmadu Bàmba");
		savedModuleTradList = addModuleTraductionList(module.getModuleId(), mapTrad);
		savedModuleTradList.forEach(savedModuleTrad->	{
			assertTrue(savedModuleTrad.getModuleTradId() != 0);
		});


		module = moduleService.findByName("Oeuvres du Mouridisme");
		mapTrad = Maps.newHashMap();
		mapTrad.put("EN", "Murid Authors writings");
		mapTrad.put("AR", "مؤلفات المريدية");
		mapTrad.put("WO", "Bindi gëstukati Murit");
		savedModuleTradList = addModuleTraductionList(module.getModuleId(), mapTrad);
		savedModuleTradList.forEach(savedModuleTrad->	{
			assertTrue(savedModuleTrad.getModuleTradId() != 0);
		});

		module = moduleService.findByName("Recherche sur le Mouridisme");
		mapTrad = Maps.newHashMap();
		mapTrad.put("EN", "Academic works on Muridiya");
		mapTrad.put("AR", "البحث عن الطريقة المريدية");
		mapTrad.put("WO", "Gëstu yu kawe ci Yoon wi");
		savedModuleTradList = addModuleTraductionList(module.getModuleId(), mapTrad);
		savedModuleTradList.forEach(savedModuleTrad->	{
			assertTrue(savedModuleTrad.getModuleTradId() != 0);
		});

		module = moduleService.findByName("Médiathèque du Mouridisme");
		mapTrad = Maps.newHashMap();
		mapTrad.put("EN", "Muridiya Media Library");
		mapTrad.put("AR", "مكتبة الوسائط المريدية");
		mapTrad.put("WO", "Ndégtal ak ceetan murit");
		savedModuleTradList = addModuleTraductionList(module.getModuleId(), mapTrad);
		savedModuleTradList.forEach(savedModuleTrad->	{
			assertTrue(savedModuleTrad.getModuleTradId() != 0);
		});


	}

	Categorie addCategory(Long moduleId,  String name, Boolean isAvailable){
		CategoryQuery query = new CategoryQuery();
		query.setIsAvailable(isAvailable);
		query.setName(name);
		query.setModuleId(moduleId);
		return categoryService.addUpdateCategory(query);
	}


	@Test
	void addCategoryTest() {

        List<Categorie> savedCategorieList= Lists.newArrayList();
		savedCategorieList.add(addCategory(moduleService.findByName("Ecrits de Cheikh A. Bamba").getModuleId(), "Poèmes (Qasidas)", true));
		savedCategorieList.add(addCategory(moduleService.findByName("Ecrits de Cheikh A. Bamba").getModuleId(), "Ouvrages didactiques", true));
		savedCategorieList.add(addCategory(moduleService.findByName("Ecrits de Cheikh A. Bamba").getModuleId(), "Correspondances", true));

		savedCategorieList.add(addCategory(moduleService.findByName("Oeuvres du Mouridisme").getModuleId(), "Biographies", true));
		savedCategorieList.add(addCategory(moduleService.findByName("Oeuvres du Mouridisme").getModuleId(), "Poésie", true));
		savedCategorieList.add(addCategory(moduleService.findByName("Oeuvres du Mouridisme").getModuleId(), "Wolofal", true));
		savedCategorieList.add(addCategory(moduleService.findByName("Oeuvres du Mouridisme").getModuleId(), "Manuscrits", true));
		savedCategorieList.add(addCategory(moduleService.findByName("Oeuvres du Mouridisme").getModuleId(), "Récits oraux", true));
		savedCategorieList.add(addCategory(moduleService.findByName("Oeuvres du Mouridisme").getModuleId(), "Sermons", true));
		savedCategorieList.add(addCategory(moduleService.findByName("Oeuvres du Mouridisme").getModuleId(), "Conférences", true));
		savedCategorieList.add(addCategory(moduleService.findByName("Oeuvres du Mouridisme").getModuleId(), "Musicographie", true));

		savedCategorieList.add(addCategory(moduleService.findByName("Recherche sur le Mouridisme").getModuleId(), "Livres", true));
		savedCategorieList.add(addCategory(moduleService.findByName("Recherche sur le Mouridisme").getModuleId(), "Thèses", true));
		savedCategorieList.add(addCategory(moduleService.findByName("Recherche sur le Mouridisme").getModuleId(), "Articles", true));
		savedCategorieList.add(addCategory(moduleService.findByName("Recherche sur le Mouridisme").getModuleId(), "Outils", true));

		savedCategorieList.add(addCategory(moduleService.findByName("Médiathèque du Mouridisme").getModuleId(), "Photos", true));
		savedCategorieList.add(addCategory(moduleService.findByName("Médiathèque du Mouridisme").getModuleId(), "Audios", true));
		savedCategorieList.add(addCategory(moduleService.findByName("Médiathèque du Mouridisme").getModuleId(), "Vidéos", true));
		savedCategorieList.add(addCategory(moduleService.findByName("Médiathèque du Mouridisme").getModuleId(), "Web", true));

		savedCategorieList.forEach(savedCategorie->	{
			assertTrue(savedCategorie.getCategoryId() != 0);
		});

	}

	CategorieTraduction addCategoryTraduction(Long categoryId, String codeLangue, String name){
		CategoryTraductionQuery query = new CategoryTraductionQuery();
		query.setCategoryId(categoryId);
		query.setCodeLangue(codeLangue);
		query.setName(name);
		return categoryService.addUpdateCategoryTraduction(query);
	}

	List<CategorieTraduction> addCategorieTraductionList(Long categoryId, Map<String,String> mapTrad){
		List<CategorieTraduction> savedCategorieTraductionList= Lists.newArrayList();
		if(categoryId!=null){
			for (Map.Entry<String, String> trad : mapTrad.entrySet()) {
				savedCategorieTraductionList.add(addCategoryTraduction(categoryId, trad.getKey(), trad.getValue()));
			}
		}
		return savedCategorieTraductionList;
	}

	@Test
	void addCategoryTraductionTest(){
		Categorie categorie = categoryService.findByName("Poèmes (Qasidas)");
		List<CategorieTraduction> savedCategoryTradList;
		Map<String,String> mapCategories = Maps.newHashMap();
		mapCategories.put("AR", "قصائد");
		mapCategories.put("EN", "Poems");
		mapCategories.put("WO", "Xasida");

		savedCategoryTradList = addCategorieTraductionList(categorie.getCategoryId(), mapCategories);
		savedCategoryTradList.forEach(savedCategoryTrad->	{
			assertTrue(savedCategoryTrad.getCategoryTradId() != 0);
		});



		categorie = categoryService.findByName("Ouvrages didactiques");
		mapCategories = Maps.newHashMap();
		mapCategories.put("AR", "كتب تعليمية");
		mapCategories.put("EN", "Educational Books");
		mapCategories.put("WO", "Téerey jàngle");

		savedCategoryTradList = addCategorieTraductionList(categorie.getCategoryId(), mapCategories);
		savedCategoryTradList.forEach(savedCategoryTrad->	{
			assertTrue(savedCategoryTrad.getCategoryTradId() != 0);
		});

	}

	Theme addTheme(String name, Boolean isAvailable){
		ThemeQuery query = new ThemeQuery();
		query.setIsAvailable(isAvailable);
		query.setName(name);
		return themeService.addUpdateTheme(query);
	}


	@Test
	void addThemeTest(){

		List<Theme> savedThemeList= Lists.newArrayList();

		savedThemeList.add(addTheme("Eloges du Prophète (Amdah)", true));
		savedThemeList.add(addTheme("Prières sur le Prophète (Salawât)", true));
		savedThemeList.add(addTheme("Reconnaissance à Dieu (Shukr)", true));
		savedThemeList.add(addTheme("Fondements de la religion", true));
		savedThemeList.add(addTheme("Exhortation", true));
		savedThemeList.add(addTheme("Soufisme", true));
		savedThemeList.add(addTheme("Règles de Bonne Conduite (Adab)", true));
		savedThemeList.add(addTheme("Vie et enseignements de Cheikh A. Bamba", true));

		savedThemeList.forEach(savedTheme->	{
			assertTrue(savedTheme.getThemeId() != 0);
		});

	}

	ThemeTraduction addThemeTraduction(Long themeId, String codeLangue, String name){
		ThemeTraductionQuery query = new ThemeTraductionQuery();
		query.setThemeId(themeId);
		query.setCodeLangue(codeLangue);
		query.setName(name);
		return themeService.addUpdateThemeTraduction(query);
	}

	List<ThemeTraduction> addThemeTraductionList(Long themeId, Map<String,String> mapTrad){
		List<ThemeTraduction> savedThemeTradList= Lists.newArrayList();
		if(themeId!=null){
			for (Map.Entry<String, String> trad : mapTrad.entrySet()) {
				savedThemeTradList.add(addThemeTraduction(themeId, trad.getKey(), trad.getValue()));
			}
		}
		return savedThemeTradList;
	}

	@Test
	void addThemeTraductionTest(){

		Theme theme = themeService.findByName("Eloges du Prophète (Amdah)");
		List<ThemeTraduction> savedThemeTradList;
		Map<String,String> mapTrad = Maps.newHashMap();
		mapTrad.put("EN", "Praise of the Prophet (Amdah)");
		mapTrad.put("AR", "مدح الرسول صلى الله عليه وسلم");
		mapTrad.put("WO", "Tagg Yonnent bi (saws)");
		savedThemeTradList = addThemeTraductionList(theme.getThemeId(), mapTrad);
		savedThemeTradList.forEach(savedThemeTrad->	{
			assertTrue(savedThemeTrad.getThemeTradId() != 0);
		});

		theme = themeService.findByName("Exhortation");
		savedThemeTradList= Lists.newArrayList();
		mapTrad = Maps.newHashMap();
		mapTrad.put("EN", "Exhortation");
		mapTrad.put("AR", "إرشاد");
		mapTrad.put("WO", "Soññ");
		savedThemeTradList = addThemeTraductionList(theme.getThemeId(), mapTrad);
		savedThemeTradList.forEach(savedThemeTrad->	{
			assertTrue(savedThemeTrad.getThemeTradId() != 0);
		});

	}

	@Test
	void addOeuvreTest(){

		String titreOeuvre ="Midâdi Wa Aqlâmi";

		Oeuvre oldOeuvre = oeuvreService.findByTitreOeuvre(titreOeuvre);

		OeuvreQuery query = new OeuvreQuery();
		query.setOeuvreId(oldOeuvre!=null ? oldOeuvre.getOeuvreId() : null);
		query.setAchatOnline("www.amazon.com");
		query.setAcrostiche("midaadii wa aqlaami");
		query.setAuthenticite_degre("100");
		query.setAvantages("baax na lool");
		query.setDispo_oeuvre(true);
		query.setDiwanOrigine(diwanService.findByName("Amdah").getDiwanId());
		query.setDiwanPage("50");
		query.setEdition("Minan");
		query.setFormeRques("formrques");
		query.setGenre("panegerique");
		query.setMetriqueNom("Basiit");
		query.setModesLecture("drouss");
		query.setNbVers(124);
		query.setIsPdfOeuvre(true);
		query.setPeriode("Pre Exil");
		query.setPeriodeDatation("1913");
		query.setPeriodeLieu("Diourbel");
		query.setPeriodeRques("");
		query.setPremierVers("Midadii wa aqlaami liman zahzahal yammaa");
		query.setPresentation("texte");
		Set<Long> refAuthors = Sets.newHashSet();

		refAuthors.add(authorService.getAuthorByName("Cheikh Ahmadou Bamba").getAuthorId());
		query.setRefAuthors(refAuthors);

		query.setCategoryId(categoryService.findByName("Poèmes (Qasidas)").getCategoryId());
		query.setRemarques("Pas de remarques");
		query.setRime("Awu");
		query.setThemePrincipal(themeService.findByName("Eloges du Prophète (Amdah)").getThemeId());
		query.setTitreOeuvre(titreOeuvre);
		query.setTitrePopulaire("Nawaytu");
		query.setTitre("مدادى و اقلامى");
		query.setUrlOeuvre("http://khassidaenpdf.free.fr/khassida_pdf/Midadi%20-%20Traduction%20en%20francais.pdf");

		Oeuvre oeuvreSaved = oeuvreService.addOrUpdateOeuvre(query);
		assertTrue(oeuvreSaved.getOeuvreId()!=0);
	}

	OeuvreTraduction addOeuvreTraduction(Long oeuvreId, String codeLangue, String titre, String traductionTitre, String avantages, String genre){
		OeuvreTraductionQuery query = new OeuvreTraductionQuery();
		query.setOeuvreId(oeuvreId);
		query.setTitre(titre);
		query.setTraductionTitre(traductionTitre);
		query.setAvantages(avantages);
		query.setCodeLangue(codeLangue);
		query.setGenre(genre);
		return oeuvreService.addUpdateOeuvreTraduction(query);
	}

	List<OeuvreTraduction> addOeuvreTraductionList(Long oeuvreId, Map<String,OeuvreTraductionQuery> mapTrad){
		List<OeuvreTraduction> savedOeuvreTradList= Lists.newArrayList();
		if(oeuvreId!=null){
			for (Map.Entry<String, OeuvreTraductionQuery> trad : mapTrad.entrySet()) {
				OeuvreTraductionQuery q = trad.getValue();
				savedOeuvreTradList.add(addOeuvreTraduction(oeuvreId, trad.getKey(), q.getTitre(), q.getTraductionTitre(), q.getAvantages(), q.getGenre()));
			}
		}
		return savedOeuvreTradList;
	}

	@Test
	void addOeuvreTraductionTest(){

		Oeuvre oeuvre = oeuvreService.findByTitreOeuvre("Midâdi Wa Aqlâmi");

		List<OeuvreTraduction> savedOeuvreTradList;

		Map<String,OeuvreTraductionQuery> mapTrad = Maps.newHashMap();

		OeuvreTraductionQuery query = new OeuvreTraductionQuery();
		query.setOeuvreId(oeuvre.getOeuvreId());
		query.setCodeLangue("EN");
		query.setTitre("Midâdi");
		query.setTraductionTitre("my ink and my pen");
		query.setAvantages("This poem makes the person love the Prophet deeply");
		query.setGenre("Praise");
		mapTrad.put("EN", query);


		savedOeuvreTradList = addOeuvreTraductionList(oeuvre.getOeuvreId(), mapTrad);
		savedOeuvreTradList.forEach(savedOeuvreTrad->	{
			assertTrue(savedOeuvreTrad.getOeuvreTradId() != 0);
		});

		query = new OeuvreTraductionQuery();
		query.setOeuvreId(oeuvre.getOeuvreId());
		query.setCodeLangue("ZH");
		query.setTitre("Midâdi");
		query.setTraductionTitre("我的墨水和笔");
		query.setAvantages("这首诗使人深深地爱着先知");
		query.setGenre("赞美");
		mapTrad.put("ZH", query);


		savedOeuvreTradList = addOeuvreTraductionList(oeuvre.getOeuvreId(), mapTrad);
		savedOeuvreTradList.forEach(savedOeuvreTrad->	{
			assertTrue(savedOeuvreTrad.getOeuvreTradId() != 0);
		});

	}


	Chapitre addChapitre(Long oeuvreId, Long chapterId, String chapterType, Integer numChamp, Integer chapterSection,
						 String title, String theme, String plageVers,  Boolean isAvailable){

		ChapitreQuery query = new ChapitreQuery();
		query.setIsAvailable(isAvailable);
		query.setTitle(title);
		query.setNum(numChamp);
		query.setChapterId(chapterId);
		query.setOeuvreId(oeuvreId);
		query.setChapterType(chapterType);
		query.setChapterSection(chapterSection);
		query.setTheme(theme);
		query.setPlageVers(plageVers);
		return chapitreService.addUpdateChapitre(query);
	}

	@Test
	void addChapitreTest(){

		Oeuvre oldOeuvre = oeuvreService.findByTitreOeuvre("Midâdi Wa Aqlâmi");

		Long oeuvreId = oldOeuvre!=null ? oldOeuvre.getOeuvreId() : null;
		Long chapitreId = null;

		List<Chapitre> savedChapitreList= Lists.newArrayList();

		savedChapitreList.add(addChapitre( oeuvreId,  chapitreId, "Introduction", 0, 0,
				"Introduction", null, "1", true));

		savedChapitreList.add(addChapitre( oeuvreId,  chapitreId, "Chapitre", 0, 0,
				"La Foi aux Livres Révélés", null, "51-57", true));

		savedChapitreList.forEach(savedChapitre->	{
			assertTrue(savedChapitre.getChapitreId()!= 0);
		});

	}

	ChapitreTraduction addChapitreTraduction(Long chapitreId, String codeLangue, String name){
		ChapitreTraductionQuery query = new ChapitreTraductionQuery();
		query.setChapitreId(chapitreId);
		query.setCodeLangue(codeLangue);
		query.setName(name);
		return chapitreService.addUpdateChapitreTraduction(query);
	}


	List<ChapitreTraduction> addChapitreTraductionList(Long chapitreId, Map<String,String> mapTrad){
		List<ChapitreTraduction> savedChapitreTradList= Lists.newArrayList();
		if(chapitreId!=null){
			for (Map.Entry<String, String> trad : mapTrad.entrySet()) {
				savedChapitreTradList.add(addChapitreTraduction(chapitreId, trad.getKey(), trad.getValue()));
			}
		}
		return savedChapitreTradList;
	}

	@Test
	void addChapitreTraductionTest(){
		List<ChapitreTraduction> chapitreTraductionList;
		Oeuvre oldOeuvre = oeuvreService.findByTitreOeuvre("Midâdi Wa Aqlâmi");
		Long oeuvreId = oldOeuvre!=null ? oldOeuvre.getOeuvreId() : null;

		Chapitre chapitre = chapitreService.findByTitleAndOeuvreId("Introduction", oeuvreId);

		Map<String,String> mapChapitre = Maps.newHashMap();
		mapChapitre.put("AR", "مقدمة");
		mapChapitre.put("EN", "Introduction");
		mapChapitre.put("WO", "Ab Jiital");

		chapitreTraductionList = addChapitreTraductionList(chapitre.getChapitreId(), mapChapitre);
		chapitreTraductionList.forEach(savedChapitreTrad->	{
			assertTrue(savedChapitreTrad.getChapitreTradId()!= 0);
		});

	}


	Vers addVers(Long chapterId, String typeVers, Integer numVers, Integer refVersNote, String texteVersAR1,
					String texteVersAR2, String texteVersAR3, String texteVersAR4){

		VersQuery query = new VersQuery();
		query.setChapitreId(chapterId);
		query.setTypeVers(typeVers);
		query.setNumVers(numVers);
		query.setRefVersNote(refVersNote);
		query.setTexteVersAR1(texteVersAR1);
		query.setTexteVersAR2(texteVersAR2);
		query.setTexteVersAR3(texteVersAR3);
		query.setTexteVersAR4(texteVersAR3);

		return versService.addUpdateVers(query);
	}

	@Test
	void addVersTest(){

		Oeuvre oldOeuvre = oeuvreService.findByTitreOeuvre("Midâdi Wa Aqlâmi");
		Long oeuvreId = oldOeuvre!=null ? oldOeuvre.getOeuvreId() : null;

		Chapitre chapitre = chapitreService.findByTitleAndOeuvreId("Introduction", oeuvreId);

		Long chapitreId = chapitre.getChapitreId();

		List<Vers> savedVersList= Lists.newArrayList();


		savedVersList.add(addVers( chapitreId,  "Vers",  1,
				null, "أَسِيرُ مَعَ الْأَبْرَارِ حِينَ أَسِيرُ",  "وَظَنَّ الْعِدَى أَنِّي هُنَاكَ أَسِيرُ", null, null));

		savedVersList.add(addVers( chapitreId,  "Vers",  2,
				null, "مَسِيرِي مَعَ الْأَخْيَارِ لِلَّهِ بِالنَّبِيُ",  "وَمَا لِي لِغَيْرِ اللَّهِ عَوْضُ مَسِيرُُ", null, null));

		savedVersList.forEach(savedVers->	{
			assertTrue(savedVers.getVersId()!= 0);
		});

	}

	VersTraduction addVersTraduction(Long versId, String codeLangue, String texte){
		VersTraductionQuery query = new VersTraductionQuery();
		query.setVersId(versId);
		query.setCodeLangue(codeLangue);
		query.setTexte(texte);
		return versService.addUpdateVersTraduction(query);
	}

	List<VersTraduction> addVersTraductionList(Long versId, Map<String,String> mapTrad){
		List<VersTraduction> savedVersTradList= Lists.newArrayList();
		if(versId!=null){
			for (Map.Entry<String, String> trad : mapTrad.entrySet()) {
				savedVersTradList.add(addVersTraduction(versId, trad.getKey(), trad.getValue()));
			}
		}
		return savedVersTradList;
	}

	@Test
	void addVersTraductionTest(){
		List<VersTraduction> versTraductionList;
		Oeuvre oldOeuvre = oeuvreService.findByTitreOeuvre("Midâdi Wa Aqlâmi");
		Long oeuvreId = oldOeuvre!=null ? oldOeuvre.getOeuvreId() : null;

		Chapitre chapitre = chapitreService.findByTitleAndOeuvreId("Introduction", oeuvreId);
		Long chapitreId = chapitre.getChapitreId();

		//just pour test car des vers peuvent se trouver dans plusieurs oeuvres
		Vers vers = versService.findByTexteVersAR1AndTexteVersAR2( "مَسِيرِي مَعَ الْأَخْيَارِ لِلَّهِ بِالنَّبِيُ",  "وَمَا لِي لِغَيْرِ اللَّهِ عَوْضُ مَسِيرُُ");


		Map<String,String> mapVers = Maps.newHashMap();
		mapVers.put("FR", "Je marchais en fait vers DIEU en compagnie du Prophète et de ses Excellents Compagnons car ma marche ne saurait point avoir d'autre objet que DIEU LUI-MEME…");
		mapVers.put("EN", "I was in fact walking towards GOD in the company of the Prophet and his Excellent Companions because my walk could not have any other object than GOD HIMSELF");
		mapVers.put("ZH", "实际上，我在先知和他的优秀同伴的陪伴下走向神，因为我的行走除了神HIMSELF之外别无其他目标...");

		versTraductionList = addVersTraductionList(vers.getVersId(), mapVers);
		versTraductionList.forEach(savedVersTrad->	{
			assertTrue(savedVersTrad.getVersTradId()!= 0);
		});

	}

}
