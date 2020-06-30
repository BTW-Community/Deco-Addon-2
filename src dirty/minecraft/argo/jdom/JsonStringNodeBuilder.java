package argo.jdom;

public final class JsonStringNodeBuilder implements JsonNodeBuilder
{
    private final String builtStringNode;

    JsonStringNodeBuilder(String par1Str)
    {
        this.builtStringNode = par1Str;
    }

    public JsonStringNode func_74600_a()
    {
        return JsonNodeFactories.aJsonString(this.builtStringNode);
    }

    public JsonNode buildNode()
    {
        return this.func_74600_a();
    }
}
