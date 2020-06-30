package argo.jdom;

import java.util.Arrays;

public final class JsonNodeSelectors
{
    public static JsonNodeSelector func_74682_a(Object ... par0ArrayOfObj)
    {
        return chainOn(par0ArrayOfObj, new JsonNodeSelector(new JsonNodeSelectors_String()));
    }

    public static JsonNodeSelector func_98316_b(Object ... par0ArrayOfObj)
    {
        return chainOn(par0ArrayOfObj, new JsonNodeSelector(new JsonNodeSelectors_Number()));
    }

    public static JsonNodeSelector func_98315_c(Object ... par0ArrayOfObj)
    {
        return chainOn(par0ArrayOfObj, new JsonNodeSelector(new JsonNodeSelectors_Boolean()));
    }

    public static JsonNodeSelector func_74683_b(Object ... par0ArrayOfObj)
    {
        return chainOn(par0ArrayOfObj, new JsonNodeSelector(new JsonNodeSelectors_Array()));
    }

    public static JsonNodeSelector func_74681_c(Object ... par0ArrayOfObj)
    {
        return chainOn(par0ArrayOfObj, new JsonNodeSelector(new JsonNodeSelectors_Object()));
    }

    public static JsonNodeSelector func_74675_a(String par0Str)
    {
        return func_74680_a(JsonNodeFactories.aJsonString(par0Str));
    }

    public static JsonNodeSelector func_74680_a(JsonStringNode par0JsonStringNode)
    {
        return new JsonNodeSelector(new JsonNodeSelectors_Field(par0JsonStringNode));
    }

    public static JsonNodeSelector func_74684_b(String par0Str)
    {
        return func_74681_c(new Object[0]).with(func_74675_a(par0Str));
    }

    public static JsonNodeSelector func_74678_a(int par0)
    {
        return new JsonNodeSelector(new JsonNodeSelectors_Element(par0));
    }

    public static JsonNodeSelector func_74679_b(int par0)
    {
        return func_74683_b(new Object[0]).with(func_74678_a(par0));
    }

    private static JsonNodeSelector chainOn(Object[] par0ArrayOfObj, JsonNodeSelector par1JsonNodeSelector)
    {
        JsonNodeSelector var2 = par1JsonNodeSelector;

        for (int var3 = par0ArrayOfObj.length - 1; var3 >= 0; --var3)
        {
            if (par0ArrayOfObj[var3] instanceof Integer)
            {
                var2 = chainedJsonNodeSelector(func_74679_b(((Integer)par0ArrayOfObj[var3]).intValue()), var2);
            }
            else
            {
                if (!(par0ArrayOfObj[var3] instanceof String))
                {
                    throw new IllegalArgumentException("Element [" + par0ArrayOfObj[var3] + "] of path elements" + " [" + Arrays.toString(par0ArrayOfObj) + "] was of illegal type [" + par0ArrayOfObj[var3].getClass().getCanonicalName() + "]; only Integer and String are valid.");
                }

                var2 = chainedJsonNodeSelector(func_74684_b((String)par0ArrayOfObj[var3]), var2);
            }
        }

        return var2;
    }

    private static JsonNodeSelector chainedJsonNodeSelector(JsonNodeSelector par0JsonNodeSelector, JsonNodeSelector par1JsonNodeSelector)
    {
        return new JsonNodeSelector(new ChainedFunctor(par0JsonNodeSelector, par1JsonNodeSelector));
    }
}
