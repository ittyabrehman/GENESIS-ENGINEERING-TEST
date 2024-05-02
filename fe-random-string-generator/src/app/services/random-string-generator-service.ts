import {HttpClient} from "@angular/common/http";
import {tap} from "rxjs";
import {Injectable} from "@angular/core";

@Injectable({
  providedIn: 'root'
})
export class RandomStringGeneratorService {
  apiUrl: string = ""
  http: HttpClient | undefined

  construct(HttpClient: HttpClient) {
    this.http = HttpClient;
    this.apiUrl = 'http://localhost:8080/api/v1/randomsStringGenerator/generate';
  }

  public generateRandomString(data: any) {
    return this.http?.post(this.apiUrl, data)
      .pipe(tap((x) => this.HandleResponse(x)));
  }

  HandleResponse(response: any) {
    if (response?.Status === 500) {
      console.error(response)
    }
  }
}
