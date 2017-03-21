package com.nrusev.web.ui.components;


import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Nikolay Rusev on 21.3.2017 г..
 */
public class PagedTableComponent extends CustomComponent {
    private Table table;
    private VerticalLayout layout;

    public PagedTableComponent(String caption, Container container){
        initTable();
        initPrivateContainer();
        table.setCaption(caption);
        table.setContainerDataSource(container);
    }

    public PagedTableComponent(){
        initTable();
        initPrivateContainer();
        layout = new VerticalLayout();
        layout.addComponent(table);

        layout.addComponent(new Button("left", l->{}));
        layout.addComponent(new Button("right", l->{}));
        setCompositionRoot(layout);
    }

    private void initPrivateContainer(){
    }

    private void initTable(){
        table = new Table() {
            @Override
            protected String formatPropertyValue(Object rowId,
                                                 Object colId, Property property) {
                // Format by property type
                if (property.getType() == Date.class) {
                    SimpleDateFormat df =
                            new SimpleDateFormat("dd MMM,yyyy", Locale.ENGLISH);
                    return df.format((Date) property.getValue());
                }

                return super.formatPropertyValue(rowId, colId, property);
            }
        };
    }


    public Object addItem(Object[] cells, Object itemId){
        System.out.println(table.getContainerDataSource().size());
        return table.addItem(cells,itemId);
    }

    public boolean addContainerProperty(Object propertyId, Class<?> type, Object defaultValue){
        return this.table.addContainerProperty(propertyId,type,defaultValue);
    }

    private boolean reset(){
        return this.table.removeAllItems();
    }


    public void setPageLength(int length){
        this.table.setPageLength(length);
    }

}
