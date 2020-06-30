package argo.jdom;

import java.util.Map;

final class JsonNodeSelectors_Field extends LeafFunctor
{
    /** fieldName in the actual Argo source. */
    final JsonStringNode theFieldName;

    JsonNodeSelectors_Field(JsonStringNode par1JsonStringNode)
    {
        this.theFieldName = par1JsonStringNode;
    }

    public boolean func_74641_a(Map par1Map)
    {
        return par1Map.containsKey(this.theFieldName);
    }

    public String shortForm()
    {
        return "\"" + this.theFieldName.getText() + "\"";
    }

    public JsonNode typeSafeApplyTo(Map par1Map)
    {
        return (JsonNode)par1Map.get(this.theFieldName);
    }

    public String toString()
    {
        return "a field called [\"" + this.theFieldName.getText() + "\"]";
    }

    public Object typeSafeApplyTo(Object par1Obj)
    {
        return this.typeSafeApplyTo((Map)par1Obj);
    }

    public boolean matchesNode(Object par1Obj)
    {
        return this.func_74641_a((Map)par1Obj);
    }
}
