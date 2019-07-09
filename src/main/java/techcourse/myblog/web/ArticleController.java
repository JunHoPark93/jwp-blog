package techcourse.myblog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import techcourse.myblog.domain.Article;
import techcourse.myblog.domain.ArticleRepository;
import techcourse.myblog.dto.ArticleDto;

@Controller
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/")
    public String index(Model model) {
        List<Article> articles = articleRepository.findAll();
        model.addAttribute("articles", articles);

        return "index";
    }

    @GetMapping("/writing")
    public String articleForm(Model model) {
        return "article-edit";
    }

    @PostMapping("/articles")
    public String articleSave(@ModelAttribute ArticleDto articleDto) {
        Article article = new Article(articleDto.getTitle(), articleDto.getCoverUrl(), articleDto.getContents());
        articleRepository.add(article);

        return "article";
    }
}
