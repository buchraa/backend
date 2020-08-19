package com.mouridiyya.bibliomouride.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.mouridiyya.bibliomouride.entity.*;
import com.mouridiyya.bibliomouride.entity.Module;
import com.mouridiyya.bibliomouride.model.*;
import com.mouridiyya.bibliomouride.repository.AuthorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;


@Slf4j
@Service
public class AdminService {

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


    public  void populateDBTest(){
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
        addChapitreTest();
        addChapitreTraductionTest();


    }


    Author addAuthor(Long authorId, String name, String bio, String link){
        AuthorQuery query = new AuthorQuery();
        query.setAuthorId(authorId);
        query.setName(name);
        query.setBiography(bio);
        query.setLink(link);
        return authorService.addUpdateAuthor(query);
    }

    Long getAuthorIdByName(String authorName){
        Author author = authorService.getAuthorByName(authorName);
        return author !=null ? author.getAuthorId():null;
    }


    void addAuthorTest(){
        List<Author> savedAuthorList= Lists.newArrayList();

        savedAuthorList.add(addAuthor(getAuthorIdByName("Cheikh Ahmadou Bamba"), "Cheikh Ahmadou Bamba", "Cheikh Ahmadou Bamba Mbacké (Aḥmad ibn Muḥammad ibn Ḥabīb Allāh) dit Khadimou al-Rassoul", null));
        savedAuthorList.add(addAuthor(getAuthorIdByName("Cheikh Muhammad Moustapha Mbacké"),"Cheikh Muhammad Moustapha Mbacké", "", null));
        savedAuthorList.add(addAuthor(getAuthorIdByName("Cheikh Muhammad Bachir Mbacké"),"Cheikh Muhammad Bachir Mbacké", "", null));
        savedAuthorList.add(addAuthor(getAuthorIdByName("S. El Hadj Mbacké Khelcom"),"S. El Hadj Mbacké Khelcom", "", null));
        savedAuthorList.add(addAuthor(getAuthorIdByName("Cheikh Moussa Ka"),"Cheikh Moussa Ka", "", null));
        savedAuthorList.add(addAuthor(getAuthorIdByName("Cheikh Gueye"),"Cheikh Gueye", "", null));
        savedAuthorList.add(addAuthor(getAuthorIdByName("Mouhamadou Mbacké Diouf"),"Mouhamadou Mbacké Diouf", "", null));
        savedAuthorList.add(addAuthor(getAuthorIdByName("Abdoul Aziz Mbacké Majalis"),"Abdoul Aziz Mbacké Majalis", "", null));
        savedAuthorList.add(addAuthor(getAuthorIdByName("Cheikh Abdoul Ahad Mbacké"),"Cheikh Abdoul Ahad Mbacké", "", null));
        savedAuthorList.add(addAuthor(getAuthorIdByName("Serigne Sam Mbaye"),"Serigne Sam Mbaye", "", null));
        savedAuthorList.add(addAuthor(getAuthorIdByName("Serigne Mayib Gueye"),"Serigne Mayib Gueye", "", null));


    }

    Diwan addDiwan(String name, Boolean isAvailable){
        DiwanQuery query = new DiwanQuery();
        query.setIsAvailable(isAvailable);
        query.setName(name);
        return diwanService.addUpdateDiwan(query);
    }


    void addDiwanTest(){

        List<Diwan> savedDiwanList= Lists.newArrayList();

        savedDiwanList.add(addDiwan("Amdah", true));
        savedDiwanList.add(addDiwan("Foulkoul Mashun", true));
        savedDiwanList.add(addDiwan("Quraaniyya", true));
        savedDiwanList.add(addDiwan("Ramadaaniyaat", true));
        savedDiwanList.add(addDiwan("Mawâhibul ilaahiyya", true));

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


    void addDiwanTraductionTest(){

        Diwan diwan = diwanService.findByName("Amdah");
        List<DiwanTraduction> savedDiwanTradList;
        Map<String,String> mapTrad = Maps.newHashMap();
        mapTrad.put("EN", "Diwan Praise");
        mapTrad.put("AR", "ديوان الامداح");
        mapTrad.put("WO", "Diwaanu tagg");
        savedDiwanTradList = addDiwanTraductionList(diwan.getDiwanId(), mapTrad);

        diwan = diwanService.findByName("Foulkoul Mashun");
        savedDiwanTradList= Lists.newArrayList();
        mapTrad = Maps.newHashMap();
        mapTrad.put("EN", "Diwan of fulku");
        mapTrad.put("AR", "ديوان الفلك");
        mapTrad.put("WO", "Diwaanu fulku");
        savedDiwanTradList = addDiwanTraductionList(diwan.getDiwanId(), mapTrad);


    }

    Module addModule(String name, Boolean isAvailable){
        ModuleQuery query = new ModuleQuery();
        query.setIsAvailable(isAvailable);
        query.setName(name);
        return moduleService.addUpdateModule(query);
    }


    void addModuleTest(){
        List<Module> savedModuleList= Lists.newArrayList();

        savedModuleList.add(addModule("Ecrits de Cheikh A. Bamba", true));
        savedModuleList.add(addModule("Oeuvres du Mouridisme", true));
        savedModuleList.add(addModule("Recherche sur le Mouridisme", true));
        savedModuleList.add(addModule("Médiathèque du Mouridisme", true));

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

    void addModuleTraductionTest(){
        Module module = moduleService.findByName("Ecrits de Cheikh A. Bamba");
        List<ModuleTraduction> savedModuleTradList;
        Map<String,String> mapTrad = Maps.newHashMap();
        mapTrad.put("EN", "Sheikh A. Bamba's writings");
        mapTrad.put("AR", "كتابات الشيخ أحمد بمب");
        mapTrad.put("WO", "Mbindum Seex Ahmadu Bàmba");
        savedModuleTradList = addModuleTraductionList(module.getModuleId(), mapTrad);


        module = moduleService.findByName("Oeuvres du Mouridisme");
        mapTrad = Maps.newHashMap();
        mapTrad.put("EN", "Murid Authors writings");
        mapTrad.put("AR", "مؤلفات المريدية");
        mapTrad.put("WO", "Bindi gëstukati Murit");
        savedModuleTradList = addModuleTraductionList(module.getModuleId(), mapTrad);


        module = moduleService.findByName("Recherche sur le Mouridisme");
        mapTrad = Maps.newHashMap();
        mapTrad.put("EN", "Academic works on Muridiya");
        mapTrad.put("AR", "البحث عن الطريقة المريدية");
        mapTrad.put("WO", "Gëstu yu kawe ci Yoon wi");
        savedModuleTradList = addModuleTraductionList(module.getModuleId(), mapTrad);


        module = moduleService.findByName("Médiathèque du Mouridisme");
        mapTrad = Maps.newHashMap();
        mapTrad.put("EN", "Muridiya Media Library");
        mapTrad.put("AR", "مكتبة الوسائط المريدية");
        mapTrad.put("WO", "Ndégtal ak ceetan murit");
        savedModuleTradList = addModuleTraductionList(module.getModuleId(), mapTrad);


    }

    Categorie addCategory(Long moduleId,  String name, Boolean isAvailable){
        CategoryQuery query = new CategoryQuery();
        query.setIsAvailable(isAvailable);
        query.setName(name);
        query.setModuleId(moduleId);
        return categoryService.addUpdateCategory(query);
    }


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


    void addCategoryTraductionTest(){
        Categorie categorie = categoryService.findByName("Poèmes (Qasidas)");
        List<CategorieTraduction> savedCategoryTradList;
        Map<String,String> mapCategories = Maps.newHashMap();
        mapCategories.put("AR", "قصائد");
        mapCategories.put("EN", "Poems");
        mapCategories.put("WO", "Xasida");

        savedCategoryTradList = addCategorieTraductionList(categorie.getCategoryId(), mapCategories);


        categorie = categoryService.findByName("Ouvrages didactiques");
        mapCategories = Maps.newHashMap();
        mapCategories.put("AR", "كتب تعليمية");
        mapCategories.put("EN", "Educational Books");
        mapCategories.put("WO", "Téerey jàngle");

        savedCategoryTradList = addCategorieTraductionList(categorie.getCategoryId(), mapCategories);


    }

    Theme addTheme(String name, Boolean isAvailable){
        ThemeQuery query = new ThemeQuery();
        query.setIsAvailable(isAvailable);
        query.setName(name);
        return themeService.addUpdateTheme(query);
    }



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

    void addThemeTraductionTest(){

        Theme theme = themeService.findByName("Eloges du Prophète (Amdah)");
        List<ThemeTraduction> savedThemeTradList;
        Map<String,String> mapTrad = Maps.newHashMap();
        mapTrad.put("EN", "Praise of the Prophet (Amdah)");
        mapTrad.put("AR", "مدح الرسول صلى الله عليه وسلم");
        mapTrad.put("WO", "Tagg Yonnent bi (saws)");
        savedThemeTradList = addThemeTraductionList(theme.getThemeId(), mapTrad);


        theme = themeService.findByName("Exhortation");
        savedThemeTradList= Lists.newArrayList();
        mapTrad = Maps.newHashMap();
        mapTrad.put("EN", "Exhortation");
        mapTrad.put("AR", "إرشاد");
        mapTrad.put("WO", "Soññ");
        savedThemeTradList = addThemeTraductionList(theme.getThemeId(), mapTrad);


    }


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
        query.setPdfOeuvre(true);
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


        addOeuvreTraductionList(oeuvre.getOeuvreId(), mapTrad);

        query = new OeuvreTraductionQuery();
        query.setOeuvreId(oeuvre.getOeuvreId());
        query.setCodeLangue("ZH");
        query.setTitre("Midâdi");
        query.setTraductionTitre("我的墨水和笔");
        query.setAvantages("这首诗使人深深地爱着先知");
        query.setGenre("赞美");
        mapTrad.put("ZH", query);

        addOeuvreTraductionList(oeuvre.getOeuvreId(), mapTrad);


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

    void addChapitreTest(){

        Oeuvre oldOeuvre = oeuvreService.findByTitreOeuvre("Midâdi Wa Aqlâmi");

        Long oeuvreId = oldOeuvre!=null ? oldOeuvre.getOeuvreId() : null;
        Long chapitreId = null;

        List<Chapitre> savedChapitreList= Lists.newArrayList();

        savedChapitreList.add(addChapitre( oeuvreId,  chapitreId, "Introduction", 0, 0,
                "Introduction", null, "1", true));

        savedChapitreList.add(addChapitre( oeuvreId,  chapitreId, "Chapitre", 0, 0,
                "La Foi aux Livres Révélés", null, "51-57", true));


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

    }


}
