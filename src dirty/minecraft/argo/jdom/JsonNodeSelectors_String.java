package argo.jdom;

final class JsonNodeSelectors_String extends LeafFunctor
{
    public boolean func_74645_a(JsonNode par1JsonNode)
    {
        return JsonNodeType.STRING == par1JsonNode.getType();
    }

    public String shortForm()
    {
        return "A short form string";
    }

    public String func_74644_b(JsonNode par1JsonNode)
    {
        return par1JsonNode.getText();
    }

    public String toString()
    {
        return "a value that is a string";
    }

    public Object typeSafeApplyTo(Object par1Obj)
    {
        return this.func_74644_b((JsonNode)par1Obj);
    }

    public boolean matchesNode(Object par1Obj)
    {
        return this.func_74645_a((JsonNode)par1Obj);
    }
}
