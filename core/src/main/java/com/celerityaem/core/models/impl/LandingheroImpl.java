package com.celerityaem.core.models.impl;

import javax.annotation.PostConstruct;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.factory.ModelFactory;
import com.celerityaem.core.models.Landinghero;
import com.adobe.cq.wcm.core.components.models.Image;

@Model(
        adaptables = {SlingHttpServletRequest.class},
        adapters = {Landinghero.class},
        resourceType = {LandingheroImpl.RESOURCE_TYPE},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)

public class LandingheroImpl implements Landinghero {
    protected static final String RESOURCE_TYPE = "celerityaem/components/content/landinghero";

    @Self
    private SlingHttpServletRequest request;

    @OSGiService
    private ModelFactory modelFactory;

    @ValueMapValue
    private String headline;

    @ValueMapValue
    private String subheadline;

    private Image image;

    @PostConstruct
    private void init() {
        image = ModelFactory.getModelFromWrappedRequest(request, request.getResource(), Image.class);
    }

    @Override
    public String getHeadline() {
        return headline;
    }

    @Override
    public String getSubheadline() {
        return subheadline;
    }

    @Override
    public boolean isEmpty() {
        final Image componentImage = getImage();

        if (StringUtils.isBlank(headline)) {
            return true
        } else if (StringUtils.isBlank(subheadline)) {
            return true;
        } else if (componentImage == null || StringUtils.isBlank(componentImage.getSrc())) {
            return true;
        } else {
            return false;
        }
    }

    private Image getImage() {
        return image;
    }
}