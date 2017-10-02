package sharedcms.registry;

public interface IRegistrar<T>
{
	public void register(T o);
	
	public Class<?> getRegistryClassType();
	
	public RegistryPhase getPhase();
}
