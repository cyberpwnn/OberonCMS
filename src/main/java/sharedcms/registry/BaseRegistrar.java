package sharedcms.registry;

public abstract class BaseRegistrar<T> implements IRegistrar<T>
{
	private Class<?> type;
	private RegistryPhase phase;
	
	public BaseRegistrar(Class<?> type, RegistryPhase phase)
	{
		this.type = type;
		this.phase = phase;
	}
	
	@Override
	public abstract void register(T o);

	@Override
	public Class<?> getRegistryClassType()
	{
		return type;
	}

	@Override
	public RegistryPhase getPhase()
	{
		return phase;
	}
}
