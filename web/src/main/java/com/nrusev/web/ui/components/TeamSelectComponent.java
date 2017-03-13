package com.nrusev.web.ui.components;

import com.nrusev.domain.Country;
import com.nrusev.domain.Team;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.server.ClassResource;
import com.vaadin.server.Resource;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.ComboBox;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Created by Nikolay Rusev on 13.3.2017 г..
 */
public class TeamSelectComponent extends ComboBox {

    public TeamSelectComponent(List<Team> teams){
        IndexedContainer container = new IndexedContainer();
        container.addContainerProperty("title", String.class,
                null);
        container.addContainerProperty("flag", Resource.class,
                null);

        teams.forEach(t -> {
            Item item = container.addItem(t);
            item.getItemProperty("title").setValue(t.getTitle());
            item.getItemProperty("flag").setValue(new ClassResource((imageFlag(t.getCountry()))));
        });


        // Create a selection component bound
        // to the container

        this.setContainerDataSource(container);

        this.setCaption("Teams");
        // Set the caption mode to read the
        // caption directly from the 'title'
        // property of the bean
        this.setItemCaptionMode(
                AbstractSelect.ItemCaptionMode.PROPERTY);
        this.setItemCaptionPropertyId("title");
        this.setItemIconPropertyId("flag");

        // Set the appropriate filtering mode for this example
        this.setFilteringMode(FilteringMode.CONTAINS);
        this.setImmediate(true);


        // Disallow null selections
        this.setNullSelectionAllowed(false);

    }

    private static String imageFlag(final Country country){
//        "São Tomé and Príncipe";"sao-tome-and-principe";"st"
//        "Cape Verde";"cape-verde";"cv"
//        "European Union";"european-union";"eu"
//        "Cocos (Keeling) Islands";"cocos-keeling-islands";"cc"
//        "England";"england";"eng"
//        "Scotland";"scotland";"sco"
//        "Wales";"wales";"wal"
//        "Northern Ireland";"northern-ireland";"nir"
//        "Tahiti";"tahiti";"pf"
        List<String> strings = Stream.of("st", "cv", "eu", "cc", "pf").collect(toList());

        String prefix = "/static/flags/";
        String suffix = ".gif";

        return Optional.ofNullable(country).map(c->{
            String key = c.getKey();
            if(strings.contains(key))
                return "not";
            if(key.equalsIgnoreCase("eng"))
                return "england";
            if(key.equalsIgnoreCase("wal"))
                return "wales";
            if(key.equalsIgnoreCase("sco"))
                return "scotland";
            if(key.equalsIgnoreCase("nir"))
                return "northern-ireland";
            return country.getAlpha2().toLowerCase();
        }).map(s->prefix + s +suffix).orElse(prefix + "default" + suffix);
    }
}
