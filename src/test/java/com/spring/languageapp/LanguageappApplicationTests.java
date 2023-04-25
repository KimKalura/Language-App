package com.spring.languageapp;

import com.spring.languageapp.dto.LiteraryWorkRequestDTO;
import com.spring.languageapp.model.LanguageType;
import com.spring.languageapp.model.LiteraryWorkPost;
import com.spring.languageapp.model.LiteraryWorkType;
import com.spring.languageapp.repository.LiteraryWorkRepository;
import com.spring.languageapp.repository.TranslationRomanizationRepository;
import com.spring.languageapp.service.LiteraryWorkService;
import com.spring.languageapp.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.image.Kernel;
import java.time.LocalDateTime;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

//@SpringBootTest
@ExtendWith({MockitoExtension.class})
class LanguageappApplicationTests {

    @InjectMocks
    private LiteraryWorkService literaryWorkService;

    @Mock
    private LiteraryWorkRepository literaryWorkRepository;

    @Mock
    private TranslationRomanizationRepository translationRomanizationRepository;

    @Mock
    private UserService userService;


    @Test
    void contextLoads() {
    }


    @Test
    void testAddLiteraryWork_ShouldReturnAddedLiteraryWork() {
        //given
        LiteraryWorkRequestDTO literaryWorkRequestDTO = new LiteraryWorkRequestDTO(LiteraryWorkType.POETRY, "ROMANIAN", "title", "text", "HEBREW", "translated title", "translated text", "romanization text");

        //when
        LiteraryWorkPost literaryWorkRepositoryObject = new LiteraryWorkPost(1L, LiteraryWorkType.POETRY, LanguageType.ROMANIAN, LocalDateTime.now(), "title", "Sample Prose in Romanian", 0, 0, null, null, null, null);
        //when(literaryWorkService.findLiteraryWork(any())).thenReturn(literaryWorkRepositoryObject);
        when(literaryWorkRepository.save(any())).thenReturn(literaryWorkRepositoryObject);

        LiteraryWorkPost result = literaryWorkService.addLiteraryWork(literaryWorkRequestDTO);

        //then
        assertNotNull(result.getId());
        assertEquals("Sample Prose in Romanian", result.getText());

    }

/*    @Test
    public void testAddLiteraryWork_withValidProseRequest_shouldSaveAndReturnLiteraryWorkPost() {
        // given
        LiteraryWorkRequestDTO literaryWorkRequestDTO = new LiteraryWorkRequestDTO(LiteraryWorkType.PROSE,"ENGLISH", "title", "text", "RUSSIAN", "translated title", "translated text", "romanization text");
//        literaryWorkRequestDTO.setTitle("title");
//        literaryWorkRequestDTO.setLiteraryWorkType(LiteraryWorkType.PROSE);
//        literaryWorkRequestDTO.setOriginalLanguage("ENGLISH");
//        literaryWorkRequestDTO.setTitle("Sample Prose");
//        literaryWorkRequestDTO.setText("This is a sample prose text with less than 1000 words.");

//        literaryWorkRequestDTO.setRomanizationText(" dd");
//        literaryWorkRequestDTO.setTranslatedLanguage("ROMANIAN");
//        literaryWorkRequestDTO.setTranslatedText("ss");
//        literaryWorkRequestDTO.setTranslatedTitle("sss");

        // when
        LiteraryWorkPost result = literaryWorkService.addLiteraryWork(literaryWorkRequestDTO);

        // then
        assertNotNull(result.getId());
        assertEquals(literaryWorkRequestDTO.getLiteraryWorkType(), result.getLiteraryWorkType());
        assertEquals(LanguageType.ENGLISH, result.getOriginalLanguage());
        assertEquals(literaryWorkRequestDTO.getTitle(), result.getTitle());
        assertEquals(literaryWorkRequestDTO.getText(), result.getText());
        assertNotNull(result.getCreatedDate());
        assertEquals(userService.findLoggedInUser(), result.getUser());
    }*/


}
