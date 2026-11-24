package pl.portfolio.domain.controller.dto;

import pl.portfolio.domain.model.DictionaryObject;

import java.util.List;

public record DictionaryListResponse(List<DictionaryListResponseElement> list) {

    public static DictionaryListResponse forObjects(List<? extends DictionaryObject> objects) {
        List<DictionaryListResponseElement> elements = objects
                .stream()
                .map(DictionaryListResponse::objectToListElement)
                .toList();
        return new  DictionaryListResponse(elements);
    }

    private static DictionaryListResponseElement objectToListElement(DictionaryObject object) {
        String caption = object.getCaption();
        String id = object.getId();
        return new DictionaryListResponseElement(caption, id);
    }

    public static DictionaryListResponse forEnums(List<Enum> objects) {
        List<DictionaryListResponseElement> elements = objects
                .stream()
                .map(DictionaryListResponse::enumToListElement)
                .toList();
        return new  DictionaryListResponse(elements);
    }

    private static DictionaryListResponseElement enumToListElement(Enum object) {
        //TODO localization for enums
        String caption = "";
        String id = object.name();
        return new DictionaryListResponseElement(caption, id);
    }

}
