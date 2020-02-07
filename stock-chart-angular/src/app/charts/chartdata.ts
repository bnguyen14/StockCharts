import { Chartmetadata } from './chartmetadata';
import { Charttimeseries } from './charttimeseries';

export interface Chartdata {
    MetaData:Chartmetadata;
    TimeSeries:Charttimeseries;
}
