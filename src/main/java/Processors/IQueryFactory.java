package Processors;
/**
 * 
 * Interface class for Query Factory
 * Creates Query object
 *  
 * @author Kanchana Mohan
 */
public interface IQueryFactory {

	Query getQueryHandler(String query);

}
