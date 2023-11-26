import { environment } from 'src/environments/environment';

export function actualUrl (url: string) {
    console.log(environment.apiUrl);
    console.log(environment.production)
    if (environment.production) {
        return environment.apiUrl + url;
    } else {
        return url;
    }
}