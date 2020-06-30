package argo.jdom;

import java.util.List;
import java.util.Map;

public abstract class JsonNode
{
    public abstract JsonNodeType getType();

    public abstract String getText();

    public abstract Map getFields();

    public abstract List getElements();

    public final Boolean getBooleanValue(Object ... par1ArrayOfObj)
    {
        return (Boolean)this.wrapExceptionsFor(JsonNodeSelectors.func_98315_c(par1ArrayOfObj), this, par1ArrayOfObj);
    }

    public final String getStringValue(Object ... par1ArrayOfObj)
    {
        return (String)this.wrapExceptionsFor(JsonNodeSelectors.func_74682_a(par1ArrayOfObj), this, par1ArrayOfObj);
    }

    public final String getNumberValue(Object ... par1ArrayOfObj)
    {
        return (String)this.wrapExceptionsFor(JsonNodeSelectors.func_98316_b(par1ArrayOfObj), this, par1ArrayOfObj);
    }

    public final boolean isArrayNode(Object ... par1ArrayOfObj)
    {
        return JsonNodeSelectors.func_74683_b(par1ArrayOfObj).matches(this);
    }

    public final List getArrayNode(Object ... par1ArrayOfObj)
    {
        return (List)this.wrapExceptionsFor(JsonNodeSelectors.func_74683_b(par1ArrayOfObj), this, par1ArrayOfObj);
    }

    private Object wrapExceptionsFor(JsonNodeSelector par1JsonNodeSelector, JsonNode par2JsonNode, Object[] par3ArrayOfObj) throws JsonNodeDoesNotMatchPathElementsException
    {
        try
        {
            return par1JsonNodeSelector.getValue(par2JsonNode);
        }
        catch (JsonNodeDoesNotMatchChainedJsonNodeSelectorException var5)
        {
            throw JsonNodeDoesNotMatchPathElementsException.jsonNodeDoesNotMatchPathElementsException(var5, par3ArrayOfObj, JsonNodeFactories.aJsonArray(new JsonNode[] {par2JsonNode}));
        }
    }
}
