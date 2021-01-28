
import {idPayload} from './identificatorPayload';

export interface identificatorPayloadUser extends idPayload {
  surname : string;
  birthDate: number;
}
