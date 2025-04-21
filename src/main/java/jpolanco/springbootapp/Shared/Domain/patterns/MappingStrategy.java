package jpolanco.springbootapp.Shared.Domain.patterns;

public interface MappingStrategy<I, D> {

    /**
     * Maps an application object to a domain object.
     *
     * @param infrastructure the application object to toDomain
     * @return the mapped domain object
     */
    D mapToDomain(I infrastructure);

    /**
     * Maps a domain object to an application object.
     *
     * @param domain the domain object to toDomain
     * @return the mapped application object
     */
    I mapToInfrastructure(D domain);
}