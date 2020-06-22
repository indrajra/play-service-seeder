package utils.module;

import com.google.inject.Inject;
import play.http.DefaultHttpFilters;

public class AllFilters extends DefaultHttpFilters {
    @Inject
    public AllFilters(RequestIdAddFilter requestIdAddFilter) {
        super(requestIdAddFilter);
    }
}
