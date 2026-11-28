package pl.portfolio.domain.controller.dto;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import pl.portfolio.domain.model.DictionaryObject;

import java.util.List;
import java.util.Locale;

public record DictionaryListResponse(List<DictionaryListResponseElement> list) {

    public static DictionaryListResponse forObjects(List<? extends DictionaryObject> objects) {
        List<DictionaryListResponseElement> elements = objects
                .stream()
                .map(DictionaryListResponse::objectToListElement)
                .toList();
        return new  DictionaryListResponse(elements);
    }

    public static DictionaryListResponse forEnums(List<Enum> objects, String enumName, MessageSource messageSource) {
        List<DictionaryListResponseElement> elements = objects
                .stream()
                .map(e -> DictionaryListResponse.enumToListElement(e,enumName, messageSource))
                .toList();
        return new  DictionaryListResponse(elements);
    }

    private static DictionaryListResponseElement objectToListElement(DictionaryObject object) {
        String caption = object.getCaption();
        String id = object.getId();
        return new DictionaryListResponseElement(caption, id);
    }

    private static DictionaryListResponseElement enumToListElement(Enum object, String enumName, MessageSource messageSource) {
        String enumNameCode = enumName + "Enum." + object.name() + ".label";
        String caption = messageSource.getMessage(enumNameCode, null, LocaleContextHolder.getLocale());
        String id = object.name();
        return new DictionaryListResponseElement(caption, id);
    }

}
