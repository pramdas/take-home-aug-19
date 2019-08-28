# webdata-analytics

<h3>Overall design</h3>
<ul>
    <li>Read Web data from the URL - [WebDataProducer]
        <ul>
            <li>Read from Url stream - [WebDataSource]</li>
            <li>Save into DataStore - [WebDataStore]</li>
        </ul>
    </li>
    <li>Consume the data from DataStore and do the aggregation - WebDataConsumer
        <ul>
            <li>Read from DataStore - [WebDataStore]</li>
            <li>Process for aggregation - [WebDataProcessor]</li>
        </ul>
    </li>
</ul>

<h3> Running the Program</h3>
<ul>
<li>git clone git@github.com:kevin-zhu/webdata-analytics.git</li>
<li>./gradlew run</li>
</ul>

<h3> Application details </h3>
    <ul>
        <li>WebDataProducer
            <ul>
            <li>It has the reference to WebDataSource, which hook up with Website stream, 
            also it contains reference to WebData store
                <ul>
                    <li>WebDataSource is reading Website stream in parallel and save into DataStore. It also have decoder and filter so that we filter out only [session start events]</li>
                    <li>WebDataStore is partitioned so that write can be bucketed and reduce write contention when processed in parallel</li>
                </ul>
            </ul>
        </li>
        <li>WebDataConsumer
            <ul>
            <li>It has the reference to WebDataStore, which can read in parallel and send to WebDataProcessor to process.
            <ul>
                <li>WebDataStore reading is bucketed and can do reading in parallel and save into DataStore </li>
                <li>WebDataProcessor is adding data to a processor. For this project, the proessor is keyed by [device, country, title] so that it can be reduced to calculate count</li>
            </ul>
            </ul>
        </li>
    </ul>
    
<h3> Configuration </h3>

<h3> Future works </h3>
 - Aggregation is currently running using ThreadPool, it is better to run in different scheduler, so that each keyed data is routed
 to different workers, which can be in different machines.
 - Write a File-based WebDataStore so that it can be persisted into an external data store, 
 which can be accessiable by multiple machines, like S3. So that we can run Producer/Consumer separately across multiple machines
 - Add a coordinator/broker to facilitate where the input stream goes to different external data store and each consumer can consumer 
 independently.

<h3> Productionization </h3>
 - Make it Guice Injection so that we can switch different component easily.
 - More testing
 - More configuration
 - Error handling with monitoring.
 - Configuration in properties files.
 - packaging/deployment tasks/scripts

