package org.mayocat.shop.rest.provider.tenant;

import javax.inject.Inject;

import org.mayocat.shop.model.Tenant;
import org.mayocat.shop.store.StoreException;
import org.mayocat.shop.store.TenantStore;
import org.xwiki.component.annotation.Component;

import com.sun.jersey.api.core.HttpContext;

@Component
public class DefaultTenantResolver implements TenantResolver
{

    @Inject
    private TenantStore tenantStore;
    
    @Override
    public Tenant resolve(HttpContext context)
    {
        String host = context.getUriInfo().getBaseUri().getHost();
        
        try {
            Tenant t = this.tenantStore.findByHandle("demo");
            if (t == null) {
                t = new Tenant("demo");
                this.tenantStore.create(t);
            }
            return this.tenantStore.findByHandle("demo");
        }
        catch (StoreException e) {
            // log error
        }
        return null;
    }

}
