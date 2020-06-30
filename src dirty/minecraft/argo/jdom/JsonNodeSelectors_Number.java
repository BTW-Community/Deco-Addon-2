package argo.jdom;

final class JsonNodeSelectors_Number extends LeafFunctor
{
    public boolean func_98314_a(JsonNode par1JsonNode)
    {
        return JsonNodeType.NUMBER == par1JsonNode.getType();
    }

    public String shortForm()
    {
        return "A short form nullable number";
    }

    public String func_98313_b(JsonNode par1JsonNode)
    {
        return par1JsonNode.getText();
    }

    public String toString()
    {
        return "a value that is a number";
    }

    public Object typeSafeApplyTo(Object par1Obj)
    {
        return this.func_98313_b((JsonNode)par1Obj);
    }

    public boolean matchesNode(Object par1Obj)
    {
        return this.func_98314_a((JsonNode)par1Obj);
    }
}
