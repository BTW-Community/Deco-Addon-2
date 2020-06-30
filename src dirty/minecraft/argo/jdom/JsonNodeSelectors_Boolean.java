package argo.jdom;

final class JsonNodeSelectors_Boolean extends LeafFunctor
{
    public boolean func_98312_a(JsonNode par1JsonNode)
    {
        return JsonNodeType.TRUE == par1JsonNode.getType() || JsonNodeType.FALSE == par1JsonNode.getType();
    }

    public String shortForm()
    {
        return "A short form boolean";
    }

    public Boolean func_98311_b(JsonNode par1JsonNode)
    {
        return Boolean.valueOf(JsonNodeType.TRUE == par1JsonNode.getType());
    }

    public String toString()
    {
        return "a true or false";
    }

    public Object typeSafeApplyTo(Object par1Obj)
    {
        return this.func_98311_b((JsonNode)par1Obj);
    }

    public boolean matchesNode(Object par1Obj)
    {
        return this.func_98312_a((JsonNode)par1Obj);
    }
}
